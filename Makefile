GRADLEW := ./gradlew
OUTPUT_DIR := pinkwall-rom
EMULATOR := snes9x.AppImage

all: run

run: deploy
	@echo "Preparing data directory..."
	@mkdir -p $(OUTPUT_DIR)/data
	@if [ -d "assets" ]; then \
		cp -r assets/* $(OUTPUT_DIR)/data/; \
		echo "Assets copied to data directory."; \
	else \
		echo "Assets directory not found. Continuing..."; \
	fi
	@echo "Preparing output directory..."
	@mkdir -p $(OUTPUT_DIR)/output
	@echo "Running JAR file..."
	@cd $(OUTPUT_DIR) && \
	JAR_FILE=$$(find . -maxdepth 1 -name "*.jar" -type f -printf "%T@ %p\n" | sort -n | tail -1 | cut -d' ' -f2-); \
	if [ -n "$$JAR_FILE" ]; then \
		echo "Executing: java -jar $$JAR_FILE"; \
		java -jar $$JAR_FILE; \
	else \
		echo "Error: No JAR file found in $(OUTPUT_DIR)"; \
		exit 1; \
	fi
	@echo "Running Make in output directory..."
	@cd $(OUTPUT_DIR)/output && make
	@echo "Running Application..."
	@cd $(OUTPUT_DIR)/output && $(EMULATOR) ./PinkWall.sfc

deploy: build
	@echo "Cleaning output directory..."
	@rm -rf $(OUTPUT_DIR)/
	@echo "Searching for JAR file..."
	@mkdir -p $(OUTPUT_DIR)
	@if find pinkwall/out/*/ -name "*.jar" -type f | grep -q .; then \
		JAR_FILE=$$(find pinkwall/out -name "*.jar" -type f -printf "%T@ %p\n" | sort -n | tail -1 | cut -d' ' -f2-); \
		cp "$$JAR_FILE" $(OUTPUT_DIR)/; \
	else \
		echo "No JAR file found in pinkwall/out/"; \
		echo "Checking build/libs/ instead..."; \
		if find build/libs -name "*.jar" -type f | grep -q .; then \
			JAR_FILE=$$(find build/libs -name "*.jar" -type f -printf "%T@ %p\n" | sort -n | tail -1 | cut -d' ' -f2-); \
			cp "$$JAR_FILE" $(OUTPUT_DIR)/; \
		else \
			echo "No JAR file found in build/libs/ either"; \
			exit 1; \
		fi; \
	fi

build:
	@echo "Building project..."
	$(GRADLEW) build

clean:
	@echo "Cleaning project..."
	$(GRADLEW) --stop
	$(GRADLEW) clean
	@echo "Removing local build and .gradle directories..."
	rm -rf build
	rm -rf .gradle

deep-clean: clean
	@echo "Removing global Gradle caches..."
	rm -rf ~/.gradle/caches

refresh:
	@echo "Refreshing dependencies..."
	$(GRADLEW) --refresh-dependencies build

rebuild: clean build

reborn: deep-clean build

.PHONY: all build clean deep-clean refresh rebuild reborn deploy
