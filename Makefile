GRADLEW := ./gradlew
OUTPUT_DIR := pinkwall-rom

all: build

deploy: build
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
