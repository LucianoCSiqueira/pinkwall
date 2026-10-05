# PinkWall

This game is based on the album "The Wall" by the band Pink Floyd. In this game, bricks will randomly fall from the sky, and your objective is to collect them without letting any fall. If you let any fall, you lose and go to the next level. This game has 3 levels, and you lose in the last one.

## Academic Context & Ecosystem

Developed as the primary practical deliverable for a Technical High School Capstone Project (TCA — _Trabalho de Conclusão do Ciclo A_) in Information Technology at **IFPR Campus Cascavel**, **PinkWall** serves as a demonstration application validating Java-based Super Nintendo (SNES) software development.

This repository is part of an integrated three-project ecosystem:

1. **[tca-docs](https://github.com/LucianoCSiqueira/tca-docs):** Central documentation portal containing software engineering specifications, requirements analysis, and system architecture.
2. **PinkWall (This repository):** Source code, visual assets, and build artifacts for the application.
3. **[JavaSNES](https://github.com/BrunoRNS/javasnes):** The underlying Java engine/framework providing hardware abstraction and SNES binary compilation.

## Architecture & Tech Stack

* **Target Platform:** Super Nintendo Entertainment System (SNES) / Homebrew Emulators
* **Language:** Java (JDK 8+)
* **Core Engine:** [JavaSNES](https://github.com/BrunoRNS/javasnes)
* **Toolchain:** PVSNESLIB / 65816 C/Assembly Toolchain Integration
* **Recommended Emulators:** Mesen, Snes9x, bsnes/higan, lakesnes

## Project Structure

```sh
pinkwall/
├── pinkwall/src/   # Core Java source code
├── assets/         # Sprites, tilemaps, and graphics assets
└── build-rom/      # Output directory for compiled SNES ROM (.sfc)
```

## Build & Execution

### Prerequisites

1. Java Development Kit (JDK 8 or higher) configured in your system environment.
2. A compatible SNES emulator installed (e.g., **Mesen** or **Snes9x**).
3. Local environment setup for **[JavaSNES](https://github.com/BrunoRNS/javasnes)**.

### Building from Source

```bash
# Clone the repository
git clone https://github.com/LucianoCSiqueira/pinkwall.git
cd pinkwall

# Build the project C/asm65x files in your preferred output folder
./gradlew build

# Run make in the directory where the C files where generated to generate the ROM (.sfc)
cd path/to/output/
make

```

### Running the Application

Launch the generated `.sfc` binary inside the development directory using your preferred emulator:

```bash
cd path/to/output/
snes9x ./pinkwall.sfc
```

## Technical Documentation

For in-depth software engineering deliverables—including Use Case Diagrams, Class Diagrams, Functional Requirements, and Architectural Decision Records (ADRs)—visit the central project homepage:

**[Access tca-docs Documentation Portal](https://github.com/LucianoCSiqueira/tca-docs)**

## Authors & Acknowledgments

* **Luciano C. Siqueira** — Project Lead & Developer ([@LucianoCSiqueira](https://github.com/LucianoCSiqueira))
* **Bruno RNS** — Creator & Lead Maintainer of [JavaSNES](https://github.com/BrunoRNS/javasnes)
* **Institution:** Instituto Federal do Paraná (IFPR) — Campus Cascavel
