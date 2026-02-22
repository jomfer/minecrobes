# Minecraft Modding Project Instructions

## Project Overview
This is a Minecraft mod built for Minecraft version 1.20.1 using the Forge mod loader.
The Mod ID is: `minecrobes`
The primary programming language is Java 17.

## Build and Run Commands
* To compile the project: `./gradlew build`
* To run the Minecraft client for testing: `./gradlew runClient`
* To run the Minecraft data generator (for recipes/tags): `./gradlew runData`

## Coding Conventions
1.  **Registration:** All new items, blocks, and entities must be registered using Forge's `DeferredRegister` system.
2.  **Naming:** Use `snake_case` for all unlocalized names, texture files, and model files (e.g., `bacteriophage_spawn_egg.json`). Use standard `PascalCase` for Java classes.
3.  **Assets:** I will handle all 3D modeling and texturing via Blockbench/Blender. Your job is to create the required `.json` blockstate and model files to point to those textures.
4.  **No Base Edits:** Do not attempt to modify base Minecraft classes directly. Always use Mixins or the provided Forge API events to inject custom logic.

## Workflow
When asked to create a new feature:
1. Create the necessary Java classes.
2. Register the feature in the appropriate registry class.
3. Generate the placeholder `.json` data files (models, blockstates, en_us.json lang files).
4. Run `./gradlew build` to ensure the code compiles before finishing the task.