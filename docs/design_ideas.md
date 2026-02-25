# 🧫 Minecrobes Mod: Design & Progression Ideas

## Overview
Minecrobes is a gameplay-rich Minecraft mod that introduces a macroscopic battle between Pathogens and Probiotics. Players must build a functional microbiology lab to diagnose hostile outbreaks, isolate bacteriophages from environmental waste, and craft biological weapons (phages) to defend their world, all while farming friendly microbes for buffs. 

---

## ⚔️ The Microbiological War: Fictional Fauna

To blend science with Minecraft's gameplay, microbes exist as giant, interactable entities called **Macrobacteria**. 

### ☠️ Pathogens (Hostile)
- Territorial, aggressive mobs that are highly resistant to standard physical weapons (swords/bows do minimal damage).
- **Biofilms:** As they roam, they passively convert blocks under them into "Biofilm". Biofilm slows players, poisons them, and spreads like Sculk or Mycelium.
- **Defeating them:** Requires identifying their strain and hitting them with a specific **Phage Grenade** to strip their armor or kill them outright.

### 🐄 Probiotics (Friendly)
- Passive, friendly Macrobacteria that spawn naturally in Lush biomes.
- Example: `Macrobacterium bovensis` (A large black-and-white microbe reminiscent of a cow).
- Can be tamed, penned, and farmed.
- **Drops/Uses:** Produce "Protein-rich sludge", crop-growth buffs, or raw materials for medicinal potions.

---

## 💜 Core Mechanics

### 🧫 Petri Dish & Swabbing
- **Empty Petri Dish:** Crafted from glass. The primary collection tool.
- **The Swab:** Sneaking and right-clicking a Biofilm block with an Empty Petri Dish gathers an "Unknown Environmental Sample."

### 🍳 Agar & Culturing
- Players must craft **LB Medium** (sugar + protein + salt + water bucket).
- **Lab Bench (Early Game):** Combine Medium + Petri Dish to make `LB Agar Plates`. *Risk:* Pouring on an open bench has a % chance to yield a contaminated "Weed Mold" plate.
- **Incubation:** Plate the "Unknown Sample" onto the Agar and wait for it to grow into a `Cultured Plate`.

### 🛢️ Phage Sourcing (The Sludge Vat)
- Phages are the "predators" of bacteria and the only way to kill Pathogens.
- Players build a **Sludge Vat** (or use composters).
- Throw in biological waste (rotten flesh, spider eyes, crops). Over time, it ferments.
- Players use a Pipette or Dish to extract samples. Different waste inputs yield different percentage chances of finding specific wild Phages.

### 💣 Weaponization
- Once the correct wild phage is found, players must multiply it using the pathogen's cultured plate as a "host" (Soft Agar Overlay method).
- The resulting high-concentration phage is crafted into a **Phage Grenade** (Splash Potion format) to be used in combat against the Pathogen.

---

## 🔬 Research & Progression System

### 🔢 Research Table & Microscope
- **Microscope:** Put a cultured plate in to reveal its hidden NBT data (Strain name, Phage vulnerability).
- **Research Table:** Submit data and plates to generate `Research Papers` and `Research Points (RP)`.

### 📊 Lab Upgrades (The Tech Tree)
- Spend RP to unlock blueprints for advanced equipment.
- **Laminar Flow Hood:** Replaces the Lab Bench. Includes a pouring minigame to guarantee 0% contamination.
- **Autoclave:** Required for higher-tier media.
- **Biosafety Levels (BL):** - BL-1: Safe, basic lab.
  - BL-2/BL-3: Requires RP to unlock containment doors and Hazmat suits to handle deadly Nether/End strains safely.

---

## 🔖 Development Sprints (To-Do)
- [X] Implement `Petri Dish` item
- [X] Implement `Lab Bench` Block & Model
- [ ] **Sprint 1: The Lab Baseline**
  - Add `LB Medium` and `LB Agar Plate` items.
  - Add Lab Bench GUI (Input Dish + Input Medium = LB Agar Plate).
- [ ] **Sprint 2: The Outbreak**
  - Create `Biofilm` block (spreads, poisons).
  - Add swabbing mechanic: Empty Dish + Biofilm = `Unknown Sample`.
- [ ] **Sprint 3: The Diagnosis**
  - Add `Cultured Plate` tick-growth mechanic.
  - Implement the `Microscope` block to reveal plate NBT data.
- [ ] **Sprint 4: The Arsenal**
  - Create the `Sludge Vat` block and fermentation loot tables.
  - Implement `Phage Grenade` items and throwing entity logic.
- [ ] **Sprint 5: The Macrobacteria**
  - Code hostile Pathogen entities (invulnerable to swords, weak to grenades).
  - Code friendly Probiotic entities (`Macrobacterium bovensis`).


  ## 🔮 Brainstorming & Future Mechanics (Backlog)
* **Projectile Deflection:** Pathogens have a bouncy, gelatinous outer membrane. Arrows or projectiles shot at them should bounce right back at the player, forcing reliance on Phage Grenades.
* **Tier 2/3 Collection (Sampling Vials):** In the late game, swabbing deadly BL-2 or BL-3 Pathogens with an open Petri Dish will inflict severe status effects (like Wither). Players will need to craft glass **Sampling Vials** to safely transport exotic strains.
* **Plate Pouring Station:** Eventually, the basic Lab Bench won't be enough. Players will need to build a dedicated pouring station to mix specialized agar recipes (like Blood Agar) required for advanced pathogens.
* **The Incubator Block:** Move plate growth out of the player's inventory. Plates will need to be physically placed inside a powered/heated Incubator block and left for a set time before they are ready for the Microscope.