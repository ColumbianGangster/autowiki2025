import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import java.io.File
import java.math.BigDecimal
import java.math.RoundingMode

// Data classes to map the JSON structure
data class Root (
      @SerializedName("mob_ext"                    ) var mobExt                  : MobExt?,
      @SerializedName("death_explosion_ext"        ) var deathExplosionExt       : DeathExplosionExt?,
      @SerializedName("melee_ext"                  ) var meleeExt                : MeleeExt?,
      @SerializedName("ability_ext"                ) var abilityExt              : AbilityExt?,
      @SerializedName("moving_ext"                 ) var movingExt               : MovingExt?,
      @SerializedName("special_attack_physics_ext" ) var specialAttackPhysicsExt : SpecialAttackPhysicsExt?,
      @SerializedName("special_attack_ext"         ) var specialAttackExt        : SpecialAttackExt?,
      @SerializedName("type_ext"                   ) var typeExt                 : TypeExt?,
      @SerializedName("entity_blueprint_ext"       ) var entityBlueprintExt      : EntityBlueprintExt?,
      @SerializedName("sim_entity_ext"             ) var simEntityExt            : SimEntityExt?,
      @SerializedName("ui_ext"                     ) var uiExt                   : UiExt?,
      @SerializedName("spawn_ext"                  ) var spawnExt                : SpawnExt?,
      @SerializedName("possess_ext"                ) var possessExt              : PossessExt?, // necron
      @SerializedName("requirement_ext"            ) var requirementExt          : RequirementExt?,
      @SerializedName("summon_ext"                 ) var summonExt               : SummonExt?,
      @SerializedName("modifier_ext"               ) var modifierExt             : ModifierExt?,
      @SerializedName("synckill_ext"               ) var synckillExt             : SynckillExt?,
      @SerializedName("cost_ext"                   ) var costExt                 : CostExt?,
      @SerializedName("cover_ext"                  ) var coverExt                : CoverExt?,
      @SerializedName("combat_ext"                 ) var combatExt               : CombatExt?,
      @SerializedName("infiltration_ext"           ) var infiltrationExt         : InfiltrationExt?,
      @SerializedName("modifier_apply_ext"         ) var modifierApplyExt        : ModifierApplyExt?,
      @SerializedName("sight_ext"                  ) var sightExt                : SightExt?,
      @SerializedName("syncdeath_ext"              ) var syncdeathExt            : SyncdeathExt?,
      @SerializedName("health_ext"                 ) var healthExt               : HealthExt?,
    )

data class MobExt (
  @SerializedName("reference" ) var reference : String? = null,
  @SerializedName("mob_value" ) var mobValue  : String? = null,
)

data class DeathExplosion (
  @SerializedName("chance"      ) var chance     : String?     = null,
  @SerializedName("area_effect" ) var areaEffect : AreaEffect?,
)

data class DeathExplosions (
    @SerializedName("death_explosion_01" ) var deathExplosion01 : DeathExplosion?,
    @SerializedName("death_explosion_02" ) var deathExplosion02 : DeathExplosion?,
    @SerializedName("death_explosion_03" ) var deathExplosion03 : DeathExplosion?,
    @SerializedName("death_explosion_04" ) var deathExplosion04 : DeathExplosion?,
    @SerializedName("death_explosion_05" ) var deathExplosion05 : DeathExplosion?,
)

data class DeathExplosionExt (
  @SerializedName("reference"        ) var reference       : String?          = null,
  @SerializedName("chance"           ) var chance          : String?          = null,
  @SerializedName("death_explosions" ) var deathExplosions : DeathExplosions?,
)


data class InMeleeModifiers (
  @SerializedName("modifier_01" ) var modifier01 : Modifier,
  @SerializedName("modifier_02" ) var modifier02 : Modifier,
  @SerializedName("modifier_03" ) var modifier03 : Modifier,
  @SerializedName("modifier_04" ) var modifier04 : Modifier,
  @SerializedName("modifier_05" ) var modifier05 : Modifier,
  @SerializedName("modifier_06" ) var modifier06 : Modifier,
  @SerializedName("modifier_07" ) var modifier07 : Modifier,
  @SerializedName("modifier_08" ) var modifier08 : Modifier,
  @SerializedName("modifier_09" ) var modifier09 : Modifier,
  @SerializedName("modifier_10" ) var modifier10 : Modifier,
)

data class MeleeExt (
  @SerializedName("reference"        ) var reference       : String?          = null,
  @SerializedName("charge_modifiers" ) var chargeModifiers : ChargeModifiers?,
  @SerializedName("charge_range"     ) var chargeRange     : String?          = null,
  @SerializedName("in_melee_modifiers" ) var inMeleeModifiers : InMeleeModifiers?,

)

data class ChargeModifier(
    val reference: String?,
    val value: String?,
    @SerializedName("modifier_01" ) var modifier01 : Modifier?,
    @SerializedName("modifier_02" ) var modifier02 : Modifier?,
    @SerializedName("modifier_03" ) var modifier03 : Modifier?,
    @SerializedName("modifier_04" ) var modifier04 : Modifier?,
)

data class ChargeModifiers(
    val reference: String?,
    @SerializedName("charge_modifier_01" ) var chargeModifier01 : ChargeModifier?,
    @SerializedName("charge_modifier_02" ) var chargeModifier02 : ChargeModifier?,
    @SerializedName("charge_modifier_03" ) var chargeModifier03 : ChargeModifier?,
    @SerializedName("charge_modifier_04" ) var chargeModifier04 : ChargeModifier?,
)



data class Abilities (
  @SerializedName("ability_01" ) var ability01 : String? = null,
  @SerializedName("ability_02" ) var ability02 : String? = null,
  @SerializedName("ability_03" ) var ability03 : String? = null,
  @SerializedName("ability_04" ) var ability04 : String? = null,
  @SerializedName("ability_05" ) var ability05 : String? = null,
  @SerializedName("ability_06" ) var ability06 : String? = null,
  @SerializedName("ability_07" ) var ability07 : String? = null,
  @SerializedName("ability_08" ) var ability08 : String? = null,
  @SerializedName("ability_09" ) var ability09 : String? = null,
)

data class AbilityExt (
  @SerializedName("reference" ) var reference : String?    = null,
  @SerializedName("abilities" ) var abilities : Abilities?,
)

data class MovingExt (
  @SerializedName("reference"                 ) var reference               : String? = null,
  @SerializedName("turning_behavior_template" ) var turningBehaviorTemplate : String? = null,
  @SerializedName("speed_max"                 ) var speedMax                : String? = null,
)

data class SpecialAttackPhysicsExt (
  @SerializedName("reference"   ) var reference : String? = null,
  @SerializedName("get_up_time" ) var getUpTime : String? = null,
  @SerializedName("mass"        ) var mass      : String? = null,
  @SerializedName("disable_special_attack" ) var disableSpecialAttack : String? = null,
)


data class TypeExt (
  @SerializedName("reference"    ) var reference   : String?      = null,
  @SerializedName("type_surface" ) var typeSurface : TypeSurface?,
  @SerializedName("type_armour"  ) var typeArmour  : TypeArmour?,

)

data class TypeSurface(
    val reference: String?
)

data class TypeArmour (
  @SerializedName("reference" ) var reference : String? = null,
    @SerializedName("screen_name_id" ) var screenNameId : String? = null,
)

data class EntityBlueprintExt (
  @SerializedName("reference"             ) var reference           : String? = null,
  @SerializedName("scale_z"               ) var scaleZ              : String? = null,
  @SerializedName("scale_x"               ) var scaleX              : String? = null,
  @SerializedName("minimum_update_radius" ) var minimumUpdateRadius : String? = null,
  @SerializedName("animator"              ) var animator            : String? = null,
)

data class SimEntityExt(
    val reference: String?
)

data class UiExt (
  @SerializedName("reference"         ) var reference        : String? = null,
  @SerializedName("minimap_teamcolor" ) var minimapTeamcolor : String? = null,
  @SerializedName("speech_directory"  ) var speechDirectory  : String? = null,
  @SerializedName("minimap_enable"    ) var minimapEnable    : String? = null,
  @SerializedName("ui_hotkey_name"    ) var uiHotkeyName     : String? = null,
  @SerializedName("ui_info"           ) var uiInfo           : UiInfo?,
  @SerializedName("ui_index_hint"     ) var uiIndexHint      : String? = null,
)

data class SpawnExt (
  @SerializedName("reference"                ) var reference              : String? = null,
  @SerializedName("oncreate_motion_duration" ) var oncreateMotionDuration : String? = null,
  @SerializedName("oncreate_event_name"      ) var oncreateEventName      : String? = null,
  @SerializedName("oncreate_motion_name"     ) var oncreateMotionName     : String? = null
)

data class Required (
  @SerializedName("reference"              ) var reference            : String? = null,
  @SerializedName("structure_name"         ) var structureName        : String? = null,
  @SerializedName("is_display_requirement" ) var isDisplayRequirement : String? = null,
  @SerializedName("owned_count" ) var ownedCount : String? = null,
  @SerializedName("own_name"    ) var ownName    : String? = null,
  @SerializedName("global_addon_name" ) var globalAddonName : String? = null,
)

data class Requirements (
  @SerializedName("required_1" ) var required1 : Required?,
  @SerializedName("required_2" ) var required2 : Required?,
  @SerializedName("required_3" ) var required3 : Required?,
  @SerializedName("required_4" ) var required4 : Required?,
  @SerializedName("required_5" ) var required5 : Required?,
  @SerializedName("required_6" ) var required6 : Required?,
  @SerializedName("required_7" ) var required7 : Required?,
  @SerializedName("required_8" ) var required8 : Required?,
  @SerializedName("required_9" ) var required9 : Required?,
)

data class RequirementExt (
  @SerializedName("reference"        ) var reference       : String?          = null,
  @SerializedName("requirements" ) var requirements : Requirements?,
)

data class HelpTextList (
  @SerializedName("text_01" ) var text01 : String? = null,
  @SerializedName("text_02" ) var text02 : String? = null,
  @SerializedName("text_03" ) var text03 : String? = null,
  @SerializedName("text_04" ) var text04 : String? = null,
  @SerializedName("text_05" ) var text05 : String? = null,
  @SerializedName("text_06" ) var text06 : String? = null,
  @SerializedName("text_07" ) var text07 : String? = null,
)

data class UiInfo (
  @SerializedName("help_text_list" ) var helpTextList : HelpTextList?,
  @SerializedName("screen_name_id" ) var screenNameId : String?       = null,
  @SerializedName("icon_name"      ) var iconName     : String?       = null,
)

data class PossessExt (
  @SerializedName("reference"                  ) var reference                : String?       = null,
  @SerializedName("possession_motion_duration" ) var possessionMotionDuration : String?       = null,
  @SerializedName("requirements"               ) var requirements             : Requirements?,
  @SerializedName("recharge_time"              ) var rechargeTime             : String?       = null,
  @SerializedName("possession_motion_name"     ) var possessionMotionName     : String?       = null,
  @SerializedName("squad_replacement_name"     ) var squadReplacementName     : String?       = null,
  @SerializedName("transfer_health_percentage" ) var transferHealthPercentage : String?       = null,
  @SerializedName("possession_ui_type"         ) var possessionUiType         : String?       = null,
  @SerializedName("ui_hotkey_name"             ) var uiHotkeyName             : String?       = null,
  @SerializedName("ui_info"                    ) var uiInfo                   : UiInfo?,

)

data class ArmourDamage (
  @SerializedName("armour_piercing"       ) var armourPiercing      : String?              = null,
  @SerializedName("min_damage_value"      ) var minDamageValue      : String?              = null,
  @SerializedName("max_damage"            ) var maxDamage           : String?              = null,
  @SerializedName("morale_damage"         ) var moraleDamage        : String?              = null,
  @SerializedName("min_damage"            ) var minDamage           : String?              = null,

)

data class WeaponDamage (
  @SerializedName("armour_damage" ) var armourDamage : ArmourDamage?,
)

data class ThrowData (
  @SerializedName("force_min"              ) var forceMin             : String? = null,
  @SerializedName("force_max"              ) var forceMax             : String? = null,
  @SerializedName("impact_point_x"         ) var impactPointX         : String? = null,
  @SerializedName("up_angle_min"           ) var upAngleMin           : String? = null,
  @SerializedName("direction_angle_random" ) var directionAngleRandom : String? = null,
  @SerializedName("up_angle_max"           ) var upAngleMax           : String? = null,
)

data class FilterType (
  @SerializedName("reference" ) var reference : String? = null,
)

data class AreaType (
  @SerializedName("reference" ) var reference : String? = null,
)

data class AreaEffectInformation (
  @SerializedName("filter_type" ) var filterType : FilterType?,
  @SerializedName("angle_right" ) var angleRight : String?     = null,
  @SerializedName("area_type"   ) var areaType   : AreaType?,
  @SerializedName("radius"      ) var radius     : String?     = null,
  @SerializedName("angle_left"  ) var angleLeft  : String?     = null,
  @SerializedName("remove_modifiers_with_source_entity" ) var removeModifiersWithSourceEntity : String?       = null, // necron lord

)

data class AreaEffect (
  @SerializedName("area_effect_information" ) var areaEffectInformation : AreaEffectInformation?,
  @SerializedName("weapon_damage"           ) var weaponDamage          : WeaponDamage?,
  @SerializedName("throw_data"              ) var throwData             : ThrowData?,
)

data class SpecialAttack (

  @SerializedName("duration"         ) var duration       : String?     = null,
  @SerializedName("chance"           ) var chance         : String?     = null,
  @SerializedName("area_effect"      ) var areaEffect     : AreaEffect?,
  @SerializedName("area_effect_time" ) var areaEffectTime : String?     = null,

)

data class SpecialAttacks (
  @SerializedName("special_attack_01" ) var specialAttack01 : SpecialAttack?,
  @SerializedName("special_attack_02" ) var specialAttack02 : SpecialAttack?,
  @SerializedName("special_attack_03" ) var specialAttack03 : SpecialAttack?,
  @SerializedName("special_attack_04" ) var specialAttack04 : SpecialAttack?,
  @SerializedName("special_attack_05" ) var specialAttack05 : SpecialAttack?,
  @SerializedName("special_attack_06" ) var specialAttack06 : SpecialAttack?,
  @SerializedName("special_attack_07" ) var specialAttack07 : SpecialAttack?,
  @SerializedName("special_attack_08" ) var specialAttack08 : SpecialAttack?,
  @SerializedName("special_attack_09" ) var specialAttack09 : SpecialAttack?,

)

data class SpecialAttackExt (
  @SerializedName("reference"                           ) var reference                       : String?         = null,
  @SerializedName("time_between_special_attacks"        ) var timeBetweenSpecialAttacks       : String?         = null,
  @SerializedName("special_attacks"                     ) var specialAttacks                  : SpecialAttacks?,
  @SerializedName("time_between_special_attacks_random" ) var timeBetweenSpecialAttacksRandom : String?         = null,

)

data class SummonExt(
    val reference: String?
)

data class ModifierExt(
    val reference: String?
)

data class SynckillExt(
    val reference: String?,
    val synckillInfos: Map<String, SynckillInfo>?
)

data class SynckillInfo(
    val killerInvulnerableTime: String?,
    val deadZombieTime: String?
)

data class CostExt (
  @SerializedName("reference" ) var reference : String?   = null,
  @SerializedName("time_cost" ) var timeCost  : TimeCost?,

)

data class TimeCost (
  @SerializedName("cost"         ) var cost        : Cost?,
  @SerializedName("time_seconds" ) var timeSeconds : String? = null,

)

data class Cost (
  @SerializedName("requisition" ) var requisition : String? = null,
  @SerializedName("power"       ) var power       : String? = null,
)

data class ApplicationType (
  @SerializedName("reference" ) var reference : String? = null,
)

data class Modifier(
  @SerializedName("reference"        ) var reference       : String?          = null,
  @SerializedName("application_type" ) var applicationType : ApplicationType?,
  @SerializedName("value"            ) var value           : String?          = null,
)

data class Modifiers (
  @SerializedName("modifier_01" ) var modifier01 : Modifier?,
  @SerializedName("modifier_02" ) var modifier02 : Modifier?,
  @SerializedName("modifier_03" ) var modifier03 : Modifier?,
  @SerializedName("modifier_04" ) var modifier04 : Modifier?,
  @SerializedName("modifier_05" ) var modifier05 : Modifier?,
  @SerializedName("modifier_06" ) var modifier06 : Modifier?,
  @SerializedName("modifier_07" ) var modifier07 : Modifier?,
  @SerializedName("modifier_08" ) var modifier08 : Modifier?,
  @SerializedName("modifier_09" ) var modifier09 : Modifier?,
  @SerializedName("modifier_10" ) var modifier10 : Modifier?,
)

data class Cover (
  @SerializedName("modifiers" ) var modifiers : Modifiers?,
)

data class CoverExt (
  @SerializedName("reference"      ) var reference     : String?        = null,
  @SerializedName("cover_heavy"    ) var coverHeavy    : Cover?,
  @SerializedName("cover_light"    ) var coverLight    : Cover?,
  @SerializedName("cover_negative" ) var coverNegative : Cover,
)

data class CombatExt (
  @SerializedName("reference"  ) var reference  : String?,
  @SerializedName("hardpoints" ) var hardpoints : Hardpoints?,
  @SerializedName("complex_upgrades" ) var complexUpgrades : String?     = null,
)

data class Hardpoints (
  @SerializedName("hardpoint_01" ) var hardpoint01 : Hardpoint?,
  @SerializedName("hardpoint_02" ) var hardpoint02 : Hardpoint?,
  @SerializedName("hardpoint_03" ) var hardpoint03 : Hardpoint?,
  @SerializedName("hardpoint_04" ) var hardpoint04 : Hardpoint?,
  @SerializedName("hardpoint_05" ) var hardpoint05 : Hardpoint?,
  @SerializedName("hardpoint_06" ) var hardpoint06 : Hardpoint?,
  @SerializedName("hardpoint_07" ) var hardpoint07 : Hardpoint?,
  @SerializedName("hardpoint_08" ) var hardpoint08 : Hardpoint?,
  @SerializedName("hardpoint_09" ) var hardpoint09 : Hardpoint?,
)

data class Hardpoint (
  @SerializedName("horizontal_aim_motion_variable_name"  ) var horizontalAimMotionVariableName  : String?      = null,
  @SerializedName("vertical_aim_motion_variable_name"    ) var verticalAimMotionVariableName    : String?      = null,
  @SerializedName("weapon_table"                         ) var weaponTable                      : WeaponTable?,
  @SerializedName("attack_motion_variable_name"          ) var attackMotionVariableName         : String?      = null,
  @SerializedName("shoot_motion_variable_name"           ) var shootMotionVariableName          : String?      = null,
  @SerializedName("hardpoint_weapon_variant_motion_name" ) var hardpointWeaponVariantMotionName : String?      = null,
  @SerializedName("use_for_facing"                       ) var useForFacing                     : String?      = null,

)

data class WeaponTable (
  @SerializedName("weapon_01" ) var weapon01 : Weapon?,
  @SerializedName("weapon_02" ) var weapon02 : Weapon?,
  @SerializedName("weapon_03" ) var weapon03 : Weapon?,
  @SerializedName("weapon_04" ) var weapon04 : Weapon?,
  @SerializedName("weapon_05" ) var weapon05 : Weapon?,
  @SerializedName("weapon_06" ) var weapon06 : Weapon?,
)

data class Origin (
  @SerializedName("y" ) var y : String? = null
)

data class Muzzle (
  @SerializedName("y" ) var y : String? = null
)

data class Weapon (

  @SerializedName("weapon"                      ) var weapon                  : String?,
  @SerializedName("name_for_this_weapon_choice" ) var nameForThisWeaponChoice : String?,
  @SerializedName("origin"                      ) var origin                  : Origin?,
  @SerializedName("muzzle"                      ) var muzzle                  : Muzzle?,
)

data class InfiltrationExt(
    val reference: String?,
    val infiltrationEventName: String?
)


data class ModifierApplyExt (
  @SerializedName("reference" ) var reference : String?    = null,
  @SerializedName("modifiers" ) var modifiers : Modifiers?,
)

data class SightExt (
  @SerializedName("reference"    ) var reference   : String? = null,
  @SerializedName("keen_sight_radius" ) var keenSightRadius : String? = null,
  @SerializedName("sight_radius" ) var sightRadius : String? = null,
)

data class SyncdeathExt(
    val reference: String?
)

data class HealthExt (
      @SerializedName("armour_minimum"                  ) var armourMinimum                : String? = null,
      @SerializedName("max_repairers"                   ) var maxRepairers                 : String? = null, // vehicles
      @SerializedName("get_back_up_health_percent"      ) var getBackUpHealthPercent       : String? = null, // necron
      @SerializedName("poison_spread_delay"             ) var poisonSpreadDelay            : String? = null,  
      @SerializedName("spawn_blood_splat_on_death"      ) var spawnBloodSplatOnDeath       : String? = null,
      @SerializedName("pre_death_event_delay"           ) var preDeathEventDelay           : String? = null,
      @SerializedName("regeneration_decrease_in_combat" ) var regenerationDecreaseInCombat : String? = null,
      @SerializedName("regeneration_rate"               ) var regenerationRate             : String? = null,
      @SerializedName("get_back_up_squad_proximity"     ) var getBackUpSquadProximity      : String? = null, // necron
      @SerializedName("get_back_up_chance"              ) var getBackUpChance              : String? = null, // necron
      @SerializedName("get_back_up_get_up_time"         ) var getBackUpGetUpTime           : String? = null, // necron
      @SerializedName("armour"                          ) var armour                       : String? = null,
      @SerializedName("can_be_repaired"                 ) var canBeRepaired                  : String? = null,
      @SerializedName("morale_death"                    ) var moraleDeath                  : String? = null,
      @SerializedName("reference"                       ) var reference                    : String? = null,
      @SerializedName("poison_immunity_duration"        ) var poisonImmunityDuration       : String? = null,
      @SerializedName("hitpoints"                       ) var hitpoints                    : String? = null,
      @SerializedName("poison_event"                    ) var poisonEvent                  : String? = null,
      @SerializedName("poison_damage_duration"          ) var poisonDamageDuration         : String? = null,
      @SerializedName("poison_damage"                   ) var poisonDamage                 : String? = null,
      @SerializedName("return_from_dead_duration"       ) var returnFromDeadDuration       : String? = null, // necron
      @SerializedName("spawn_death_blossom"             ) var spawnDeathBlossom            : String? = null,
      @SerializedName("spawn_usable_body_on_death"      ) var spawnUsableBodyOnDeath       : String? = null, // necron
      @SerializedName("death_event"                     ) var deathEvent                   : String? = null,
)

val gson = Gson()

// Function to generate HTML from the parsed JSON
// fun generateHtmlFromJson(jsonString: String): String {
//     // Deserialize JSON into Root object using Gson
//     val root = gson.fromJson(jsonString, Root::class.java)
//     println("Parsed object: $root")
    
//     // Generate HTML
//     return """
//         <!DOCTYPE html>
//         <html>
//         <head>
//             <title>Unit Info</title>
//             <meta charset="UTF-8">
//         </head>
//         <body>
//             <h1>Unit: ${root.uiExt?.uiInfo?.screenNameId ?: "Unknown"}</h1>

//             <h2>Help Texts</h2>
//             <ul>
//                 ${root.uiExt?.uiInfo?.helpTextList?.let {
//                     listOfNotNull(
//                         it.text01, it.text02, it.text03, it.text04, it.text05, it.text06, it.text07
//                     ).joinToString("\n") { "<li>$it</li>" }
//                 } ?: "<li>No help texts</li>"}
//             </ul>

//             <h2>Cost</h2>
//             <p>Requisition: ${root.costExt?.timeCost?.cost?.requisition ?: "N/A"}</p>
//             <p>Power: ${root.costExt?.timeCost?.cost?.power ?: "N/A"}</p>
//             <p>Build Time (seconds): ${root.costExt?.timeCost?.timeSeconds ?: "N/A"}</p>

//             <h2>Movement</h2>
//             <p>Speed: ${root.movingExt?.speedMax ?: "N/A"}</p>

//             <h2>Health Info</h2>
//             <p>Hitpoints: ${root.healthExt?.hitpoints ?: "N/A"}</p>
//             <p>Regeneration Rate: ${root.healthExt?.regenerationRate ?: "N/A"}</p>

//             <h2>Special Attack Physics</h2>
//             <p>Get Up Time: ${root.specialAttackPhysicsExt?.getUpTime ?: "N/A"}</p>
//             <p>Mass: ${root.specialAttackPhysicsExt?.mass ?: "N/A"}</p>

//             <h2>Melee Info</h2>
//             <p>Charge Range: ${root.meleeExt?.chargeRange ?: "N/A"}</p>
//             <p>Charge Modifiers:</p>
//             <ul>
//                 ${root.meleeExt?.chargeModifiers?.entries?.joinToString("\n") { 
//                     "<li>${it.key}: ${it.value?.value ?: "N/A"}</li>" 
//                 } ?: "<li>No charge modifiers</li>"}
//             </ul>

//             <h2>Type Info</h2>
//             <p>Type Armour Reference: ${root.typeExt?.typeArmour?.reference ?: "N/A"}</p>

//             <h2>Combat Info</h2>
//             <p>Hardpoint 01:</p>
//             <ul>
//                 ${root.combatExt?.hardpoints?.hardpoint01?.weaponTable?.weapon01?.weapon ?: "N/A"}
//                 <li>Weapon 01: ${root.combatExt?.hardpoints?.hardpoint01?.weaponTable?.weapon01?.weapon ?: "N/A"}</li>
//                 <li>Weapon 02: ${root.combatExt?.hardpoints?.hardpoint01?.weaponTable?.weapon02?.weapon ?: "N/A"}</li>
//             </ul>
//         </body>
//         </html>
//     """.trimIndent()
// }

fun cleanString(input: String): String {
    // Replace underscores with spaces
    var cleanedString = input.replace("_", " ")

    cleanedString = cleanedString.replace("ebpextensions\\", "")
    cleanedString = cleanedString.replace("weapon\\", "")
    cleanedString = cleanedString.replace("abilities\\", "")
    if (!cleanedString.contains("text")) {
        cleanedString = cleanedString.replace("ext", "")
    }
    cleanedString = cleanedString.replace("space marines", "")
    cleanedString = cleanedString.replace("space marine", "")
    cleanedString = cleanedString.replace("name for this weapon choice", "name")

    // Apply further cleaning if necessary:
    if (cleanedString.contains(".lua")) {
        cleanedString = cleanedString.replace(".lua", "")
    }

    if (cleanedString.contains("[[") || cleanedString.contains("]]")) {
        cleanedString = cleanedString.replace("[[", "").replace("]]", "")
    }

    // Handle number strings that can be converted to actual numbers
    val number = cleanedString.toDoubleOrNull()
    if (number != null) {
        // Check if it's a whole number, and remove decimal places if it's effectively an integer
        val roundedNumber = BigDecimal(number.toString()).setScale(2, RoundingMode.HALF_UP).toDouble()
        return if (roundedNumber == roundedNumber.toInt().toDouble()) {
            // Return the rounded number as an integer
            roundedNumber.toInt().toString()
        } else {
            // Return the number as a string with 2 decimal places
            roundedNumber.toString()
        }
    }

    return cleanedString
}


// Helper function to generate HTML table from a map
fun generateTable(data: Map<String, Any?>): String {
    val htmlTable = StringBuilder("<table border='1'>")
    data.forEach { (key, value) ->
        // Check if the value is a Map, indicating a sub-map
        if (value is Map<*, *>) {
            // If value is a Map, recursively generate a sub-table
            htmlTable.append("<td colspan='${data.size}'>")
            htmlTable.append("<strong>${cleanString(key)}</strong>: ${generateTable(value as Map<String, Any?>)}")
            htmlTable.append("</td>")
        } else {
            if (key == "reference" && value is String && !value.contains("type_armour\\")) {
                // Skip appending the row when the condition is met
                // You can either leave it empty or do any specific action if required.
                // Do nothing in this case as per your original logic.
            } else {
                // For all other cases, render as a simple string
                htmlTable.append("<tr><td>${cleanString(key)}</td><td>${cleanString(value?.toString() ?: "")}</td></tr>")
            }
        }
    }
    htmlTable.append("</table>")
    return htmlTable.toString()
}

// Recursive function to generate HTML for any object
fun generateHtml(root: Any?): String {
    if (root == null) {
        return ""
    }

    val json = gson.toJson(root)
    
    // Deserialize the root object to a Map for simple objects
    val mapType = object : TypeToken<Map<String, Any?>>() {}.type
    val map: Map<String, Any?> = gson.fromJson(json, mapType)

    val htmlContent = StringBuilder()

    // For complex objects, recursively process sections if they are instances of Root
    if (root is Root) {
        htmlContent.append(generateSection("UiExt", root.uiExt))
        htmlContent.append(generateSection("HealthExt", root.healthExt))
        htmlContent.append(generateSection("TypeExt", root.typeExt))
        htmlContent.append(generateSection("CostExt", root.costExt))
        htmlContent.append(generateSection("MobExt", root.mobExt))
        htmlContent.append(generateSection("MovingExt", root.movingExt))
        htmlContent.append(generateSection("SightExt", root.sightExt))
        htmlContent.append(generateSection("RequirementExt", root.requirementExt))
        htmlContent.append(generateSection("CombatExt", root.combatExt))
        htmlContent.append(generateSection("DeathExplosionExt", root.deathExplosionExt))
        htmlContent.append(generateSection("MeleeExt", root.meleeExt))
        htmlContent.append(generateSection("AbilityExt", root.abilityExt))
        htmlContent.append(generateSection("SpecialAttackPhysicsExt", root.specialAttackPhysicsExt))
        htmlContent.append(generateSection("SpecialAttackExt", root.specialAttackExt))
        htmlContent.append(generateSection("EntityBlueprintExt", root.entityBlueprintExt))
        htmlContent.append(generateSection("SimEntityExt", root.simEntityExt))
        htmlContent.append(generateSection("SpawnExt", root.spawnExt))
        htmlContent.append(generateSection("PossessExt", root.possessExt))
        htmlContent.append(generateSection("SummonExt", root.summonExt))
        htmlContent.append(generateSection("ModifierExt", root.modifierExt))
        htmlContent.append(generateSection("SynckillExt", root.synckillExt))
        htmlContent.append(generateSection("CoverExt", root.coverExt))
        htmlContent.append(generateSection("InfiltrationExt", root.infiltrationExt))
        htmlContent.append(generateSection("ModifierApplyExt", root.modifierApplyExt))
        htmlContent.append(generateSection("SyncdeathExt", root.syncdeathExt))
    } else {
        // For simple objects, generate a table
        if (map.isNotEmpty()) {
            htmlContent.append(generateTable(map))
        }
    }

    // Include other nested sections as necessary
    return htmlContent.toString()
}

// Helper function to generate HTML for a nested object
fun generateSection(title: String, data: Any?): String {
    return if (data == null) {
        ""
    } else {
        "<h3>$title</h3>${generateHtml(data)}"
    }
}

// Function to generate HTML from a JSON string
fun generateHtmlFromJson2(jsonString: String): String {
    val root = gson.fromJson(jsonString, Root::class.java)

    // Start HTML page with added styles
    return """
        <html>
        <head>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    margin: 20px;
                    padding: 10px;
                    background-color: #f4f4f9;
                }

                h3 {
                    color: #4CAF50;
                    border-bottom: 2px solid #4CAF50;
                    padding-bottom: 5px;
                }

                table {
                    width: 100%;
                    margin-bottom: 20px;
                    border-collapse: collapse;
                    border: 1px solid #ddd;
                }

                th, td {
                    padding: 8px;
                    text-align: left;
                    border-bottom: 1px solid #ddd;
                }

                tr:nth-child(even) {
                    background-color: #f2f2f2;
                }

                tr:hover {
                    background-color: #ddd;
                }

                .section {
                    margin-bottom: 30px;
                }

                .section h3 {
                    margin-top: 20px;
                }

                /* Top Navigation Bar Styles */
                nav {
                    background-color: #333;
                    padding: 10px 20px;
                    display: flex;
                    justify-content: space-between;
                    align-items: center;
                }

                nav a {
                    color: white;
                    text-decoration: none;
                    padding: 10px 15px;
                    font-size: 16px;
                    text-transform: uppercase;
                    letter-spacing: 1px;
                    border-radius: 5px;
                    transition: background-color 0.3s;
                }

                nav a:hover {
                    background-color: #575757;
                }

                .logo {
                    font-size: 20px;
                    font-weight: bold;
                    color: white;
                }

                /* Right-aligned links (e.g., for other sections or logout) */
                .nav-links {
                    display: flex;
                }

                .nav-links a {
                    margin-left: 20px;
                }
            </style>
        </head>
        <body>
            ${generateHtml(root)}
        </body>
        </html>
    """.trimIndent()
}

// Function to process all JSON files in a directory recursively
fun processJsonFiles(inputDir: File, outputDir: File) {
    if (!inputDir.exists()) {
        println("Input directory does not exist: ${inputDir.absolutePath}")
        return
    }

    inputDir.walkTopDown().forEach { file ->
        if (file.isFile && file.extension.lowercase() == "json") {
            val relativePath = file.relativeTo(inputDir)
            val outputFile = File(outputDir, relativePath.path.replaceAfterLast('.', "html"))

            // Ensure parent directories exist
            outputFile.parentFile.mkdirs()

            try {
                val jsonString = file.readText()
                val htmlContent = generateHtmlFromJson2(jsonString)
                outputFile.writeText(htmlContent)
                // println("Processed: ${file.absolutePath} -> ${outputFile.absolutePath}")
            } catch (e: Exception) {
                println("Failed to process ${file.absolutePath}: ${e.message}")
            }
        }
    }
}

fun main() {
    val inputFolder = File("./rawjson/ebps/races/")
    val outputFolder = File("ebps_html")

    processJsonFiles(inputFolder, outputFolder)
}

main()
