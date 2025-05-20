# 🧫 Minecrobes Mod: Design & Progression Ideas

## Overview
Minecrobes is an educational and gameplay-rich Minecraft mod that introduces microbial life, including bacteria and bacteriophages. Players can craft Petri dishes, culture microbes, and build up a research infrastructure to explore increasingly complex biological mechanics. The mod blends science with exploration, progression, and crafting.

---

## 💜 Core Mechanics

### 💪 Petri Dish System
- Acts like a "Pokéball" for microbes.
- Must be crafted and prepared before use.
- Comes in various types:
  - Empty Petri Dish
  - LB Agar Petri Dish
  - Specialized Medium Petri Dishes (e.g. Blood Agar)

### 🍳 Agar & Media Preparation
- Crafted from realistic ingredients:
  - LB Medium = sugar + protein source + salt + water bucket
  - Specialized media crafted with additional or rare ingredients
- Players must combine the medium with an empty Petri Dish to prepare culture-ready plates

### 🧬 Microbe Culturing
- Plates can be inoculated with bacterial strains
- Incubation mechanics could involve time, heat, or proximity
- Plates visibly change to show microbial growth

### 🦠 Phage Collection
- Requires a prepared bacterial plate ("host")
- Player adds phage sample via overlay (soft agar system)
- Successful infection results in visible plaques
- Phages can be harvested as entities or items

---

## 🔬 Research & Progression System

### 🔢 Research Table
- Custom block where players place samples + research notebook
- Produces:
  - `Research Paper` items
  - `Research Points` (RP) currency

### 🌈 What You Can Research
- New bacteria/phage types
- Resistance traits or host range
- Media optimization
- Rare biome discoveries

### 📊 Research Unlocks
- Lab equipment upgrades (e.g. centrifuge, incubator)
- Access to biosafety level infrastructure (BL-2, BL-3)
- Crafting unlocks for media, gear, and diagnostic tools
- Access to exotic microbes from:
  - Caves
  - Nether
  - Hostile mobs

---

## ⚠️ Biosafety Levels (BL System)
- BL-1: Default lab, safe soil strains
- BL-2: Requires research unlock; handles pathogens
- BL-3: Requires high RP; dangerous phages & exotic cave organisms
- Could include:
  - Hazmat suit requirement
  - Lab containment door blocks
  - Safety violations trigger contamination events

---

## 🧠 Expanded Equipment & Minigames

### 🧪 Lab Equipment & Flow
- Chemical hood: used to craft media ingredients.
- Autoclave: required to sterilize liquid medium.
- Laminar flow hood: used for pouring plates.
  - Features a **minigame**: Hold until a bar reaches a sweet spot to pour the correct amount of agar.
  - Plate quality scales with precision.
  - Higher-tier research plates require greater precision.

---

## 🐄 Macrobacteria: Fictional Fauna
To complement the realistic microbiology side, the mod includes fictional macroscopic bacteria:
- Example: `Macrobacterium bovensis`
  - A large black-and-white microbe reminiscent of a cow
  - Can replace passive mobs in specific biomes
  - Drops microbe-relevant loot (e.g. protein-rich sludge)
- These creatures are deliberately labeled as fictional and given fictional taxonomy to separate them from real microbiology.

---

## 📘 Educational Elements
- Ingredient books describing real lab procedures
- In-game book or UI describing:
  - LB medium recipe
  - Soft agar overlay
  - How phages work
  - Resistance mechanisms
- Research table could summarize real-world references with simplified explanations

---

## 🔖 To-Do
- [X] Implement `Petri Dish` item
- [x] Make screenshot of Steve and petri dish for github page
- [ ] Add `LB Agar` crafting system
- [ ] Prototype microbial culture mechanic
- [ ] Add `Research Table` block
- [ ] Track `Research Points` via NBT or player capability
- [ ] Design first tech tree of research unlocks
- [ ] Implement BL access tiers
- [ ] Add autoclave, chemical hood, and laminar flow hood blocks
- [ ] Implement plate pouring minigame
- [ ] Add macrobacterial fauna with fictional classification
