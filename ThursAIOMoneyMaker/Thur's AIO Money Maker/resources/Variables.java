package resources;

import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class Variables {
	// Ints
	public final static int TAR_ID = 1939;
	public final static int FLAX_ID = 2646;
	public final static int INV_FLAX = 1779;
	public final static int SEERS_BANKER = 495;
	public final static int FULL_WINE = 1993;
	public final static int JUG = 1935;
	public final static int VARROCK_BANKER = 553;
	public final static int APPLE_PIE = 2323;
	public final static int PIE_DISH = 2313;
	public final static int HUNTER_KIT = 11159;
	public final static int NOOSE_WAND = 10150;
	public final static int BUTTERFLY_NET = 10010;
	public final static int BIRD_SNARE = 10006;
	public final static int RABBIT_SNARE = 10031;
	public final static int TEASING_STICK = 10029;
	public final static int BOX_TRAP = 10008;
	public final static int UNLIT_TORCH = 596;
	public final static int BOW_STRING = 1777;
	public final static int LADDER_ID = 25938;
	public final static int LADDER_ID2 = 25939;
	public final static int CLOSED_DOOR = 25819;
	public final static int SPINNER = 25824;
	public final static int SARA_BREW_FULL = 6689;
	public final static int SARA_BREW_DRANK = 6691;
	public final static int BONE_ID = 526;
	public final static int LUMBY_BOX = 79036;
	public final static int COW_HIDES = 1739;
	public final static int FEATHER_ID = 314;
	public final static int UNICORN_ID = 89;
	public final static int HORN_ID = 237;
	public final static int HORN_BANKER = 2759;
	public final static int VIALS = 229;
	public final static int WATER_VIALS = 227;
	public final static int FOUNTAIN_ID = 47150;
	public final static int VIAL_BANKER = 2718;
	public final static int CHOCOLATE_BAR = 1973;
	public final static int CHOCOLATE_DUST = 1975;

	// Booleans
	public static boolean takeHides = false;
	public static boolean tarPickup = false;
	public static boolean flaxPicking = false;
	public static boolean drinkWine = false;
	public static boolean eatPie = false;
	public static boolean openKits = false;
	public static boolean spinFlax = false;
	public static boolean drinkBrews = false;
	public static boolean pickupBones = false;
	public static boolean pickupHides = false;
	public static boolean pickupFeathers = false;
	public static boolean unicornKill = false;
	public static boolean fillVials = false;
	public static boolean crushBars = false;

	// Strings
	public static String method;
	public static String status;

	// Other
	static long startTime = System.currentTimeMillis();
	public final static Timer runTime = new Timer(0);
	static long time = System.currentTimeMillis();
	public static SceneObject fount;
	public static Timer Sleeping = new Timer(0);

	// Areas
	public static final Area TAR_LOCATION = new Area(new Tile[] {
			new Tile(3187, 3178, 0), new Tile(3188, 3183, 0),
			new Tile(3188, 3188, 0), new Tile(3187, 3194, 0),
			new Tile(3187, 3199, 0), new Tile(3186, 3204, 0),
			new Tile(3185, 3209, 0), new Tile(3182, 3214, 0),
			new Tile(3177, 3215, 0), new Tile(3172, 3216, 0),
			new Tile(3167, 3216, 0), new Tile(3162, 3217, 0),
			new Tile(3157, 3218, 0), new Tile(3152, 3219, 0),
			new Tile(3147, 3219, 0), new Tile(3142, 3217, 0),
			new Tile(3138, 3213, 0), new Tile(3135, 3209, 0),
			new Tile(3133, 3204, 0), new Tile(3132, 3199, 0),
			new Tile(3132, 3194, 0), new Tile(3131, 3189, 0),
			new Tile(3131, 3184, 0), new Tile(3130, 3179, 0),
			new Tile(3130, 3174, 0), new Tile(3129, 3169, 0),
			new Tile(3128, 3164, 0), new Tile(3129, 3159, 0),
			new Tile(3130, 3154, 0), new Tile(3133, 3149, 0),
			new Tile(3135, 3144, 0), new Tile(3139, 3140, 0),
			new Tile(3143, 3137, 0), new Tile(3147, 3134, 0),
			new Tile(3151, 3131, 0), new Tile(3156, 3129, 0),
			new Tile(3162, 3126, 0), new Tile(3168, 3125, 0),
			new Tile(3174, 3123, 0), new Tile(3180, 3122, 0),
			new Tile(3186, 3120, 0), new Tile(3191, 3120, 0),
			new Tile(3196, 3120, 0), new Tile(3201, 3120, 0),
			new Tile(3206, 3121, 0), new Tile(3211, 3123, 0),
			new Tile(3216, 3125, 0), new Tile(3220, 3128, 0),
			new Tile(3224, 3131, 0), new Tile(3227, 3135, 0),
			new Tile(3231, 3138, 0), new Tile(3233, 3143, 0),
			new Tile(3235, 3148, 0), new Tile(3235, 3153, 0),
			new Tile(3235, 3158, 0), new Tile(3235, 3163, 0),
			new Tile(3237, 3168, 0), new Tile(3239, 3173, 0),
			new Tile(3241, 3178, 0), new Tile(3242, 3183, 0),
			new Tile(3243, 3188, 0), new Tile(3240, 3193, 0),
			new Tile(3235, 3195, 0), new Tile(3231, 3198, 0),
			new Tile(3226, 3199, 0), new Tile(3221, 3201, 0),
			new Tile(3216, 3203, 0), new Tile(3211, 3205, 0),
			new Tile(3205, 3207, 0), new Tile(3200, 3210, 0),
			new Tile(3195, 3212, 0), new Tile(3190, 3212, 0),
			new Tile(3185, 3212, 0) });

	public static final Area FLAX_AREA = new Area(new Tile[] {
			new Tile(2743, 3442, 0), new Tile(2743, 3447, 0),
			new Tile(2742, 3452, 0), new Tile(2747, 3452, 0),
			new Tile(2751, 3449, 0), new Tile(2752, 3444, 0),
			new Tile(2752, 3439, 0), new Tile(2749, 3435, 0),
			new Tile(2744, 3435, 0), new Tile(2739, 3435, 0),
			new Tile(2736, 3439, 0), new Tile(2734, 3444, 0),
			new Tile(2734, 3449, 0), new Tile(2737, 3453, 0),
			new Tile(2742, 3453, 0), new Tile(2743, 3448, 0) });

	public static final Area SEERS_BANK_AREA = new Area(new Tile[] {
			new Tile(2725, 3492, 0), new Tile(2725, 3497, 0),
			new Tile(2724, 3502, 0), new Tile(2730, 3504, 0),
			new Tile(2734, 3501, 0), new Tile(2737, 3496, 0),
			new Tile(2738, 3491, 0), new Tile(2738, 3486, 0),
			new Tile(2734, 3482, 0), new Tile(2730, 3479, 0),
			new Tile(2725, 3479, 0), new Tile(2720, 3479, 0),
			new Tile(2717, 3483, 0), new Tile(2715, 3488, 0),
			new Tile(2713, 3493, 0), new Tile(2714, 3498, 0),
			new Tile(2717, 3502, 0), new Tile(2722, 3502, 0) });

	public static final Area VARROCK_BANK = new Area(new Tile[] {
			new Tile(3184, 3438, 0), new Tile(3183, 3443, 0),
			new Tile(3183, 3448, 0), new Tile(3188, 3450, 0),
			new Tile(3193, 3448, 0), new Tile(3196, 3444, 0),
			new Tile(3198, 3439, 0), new Tile(3199, 3434, 0),
			new Tile(3199, 3429, 0), new Tile(3194, 3428, 0),
			new Tile(3189, 3428, 0), new Tile(3184, 3428, 0),
			new Tile(3179, 3429, 0), new Tile(3175, 3432, 0),
			new Tile(3172, 3436, 0), new Tile(3172, 3441, 0),
			new Tile(3173, 3446, 0), new Tile(3177, 3449, 0),
			new Tile(3182, 3449, 0) });

	public static final Area SPINNER_AREA = new Area(new Tile[] {
			new Tile(2711, 3470, 0), new Tile(2710, 3475, 0),
			new Tile(2715, 3476, 0), new Tile(2720, 3474, 0),
			new Tile(2723, 3470, 0), new Tile(2719, 3467, 0),
			new Tile(2714, 3466, 0), new Tile(2709, 3468, 0),
			new Tile(2708, 3473, 0), new Tile(2709, 3478, 0) });

	public static final Area GLITCH_AREA = new Area(new Tile[] {
			new Tile(2737, 3501, 0), new Tile(2733, 3504, 0),
			new Tile(2731, 3509, 0), new Tile(2735, 3512, 0),
			new Tile(2740, 3511, 0), new Tile(2742, 3506, 0),
			new Tile(2743, 3501, 0), new Tile(2741, 3496, 0),
			new Tile(2736, 3495, 0), new Tile(2731, 3497, 0),
			new Tile(2729, 3502, 0), new Tile(2727, 3507, 0),
			new Tile(2730, 3511, 0) });

	public static final Area BONE_AREA = new Area(new Tile[] {
			new Tile(3237, 3252, 0), new Tile(3270, 3254, 0),
			new Tile(3270, 3213, 0), new Tile(3250, 3213, 0),
			new Tile(3234, 3237, 0) });

	public static Area BONE_COW_AREA = new Area(new Tile[] {
			new Tile(3268, 3252, 0), new Tile(3243, 3252, 0),
			new Tile(3239, 3298, 0), new Tile(3268, 3297, 0) });

	static final Area LUMBY_AREA = new Area(new Tile[] {
			new Tile(3209, 3219, 0), new Tile(3208, 3224, 0),
			new Tile(3207, 3229, 0), new Tile(3210, 3233, 0),
			new Tile(3215, 3232, 0), new Tile(3220, 3230, 0),
			new Tile(3225, 3228, 0), new Tile(3230, 3227, 0),
			new Tile(3233, 3222, 0), new Tile(3233, 3217, 0),
			new Tile(3230, 3213, 0), new Tile(3228, 3208, 0),
			new Tile(3223, 3206, 0), new Tile(3218, 3204, 0),
			new Tile(3213, 3203, 0), new Tile(3208, 3202, 0),
			new Tile(3203, 3202, 0), new Tile(3200, 3206, 0),
			new Tile(3198, 3211, 0), new Tile(3198, 3216, 0),
			new Tile(3198, 3221, 0), new Tile(3199, 3226, 0),
			new Tile(3201, 3231, 0), new Tile(3204, 3235, 0) });

	public static Area COW_AREA = new Area(new Tile[] {
			new Tile(3267, 3255, 0), new Tile(3269, 3260, 0),
			new Tile(3270, 3265, 0), new Tile(3271, 3270, 0),
			new Tile(3271, 3275, 0), new Tile(3271, 3280, 0),
			new Tile(3269, 3285, 0), new Tile(3268, 3290, 0),
			new Tile(3268, 3295, 0), new Tile(3264, 3298, 0),
			new Tile(3259, 3299, 0), new Tile(3254, 3300, 0),
			new Tile(3249, 3300, 0), new Tile(3244, 3299, 0),
			new Tile(3239, 3297, 0), new Tile(3237, 3292, 0),
			new Tile(3238, 3287, 0), new Tile(3239, 3282, 0),
			new Tile(3242, 3278, 0), new Tile(3246, 3275, 0),
			new Tile(3246, 3270, 0), new Tile(3247, 3265, 0),
			new Tile(3249, 3260, 0), new Tile(3252, 3256, 0),
			new Tile(3253, 3251, 0), new Tile(3250, 3255, 0),
			new Tile(3269, 3252, 0) });

	public static Area COW_HIDE_ANTI_BAN = new Area(new Tile[] {
			new Tile(3239, 3289, 0), new Tile(3234, 3287, 0),
			new Tile(3233, 3282, 0), new Tile(3237, 3279, 0),
			new Tile(3242, 3278, 0) });

	static final Area DEPOSITBOX_AREA = new Area(new Tile[] {
			new Tile(3212, 3265, 0), new Tile(3210, 3260, 0),
			new Tile(3215, 3258, 0), new Tile(3218, 3262, 0),
			new Tile(3215, 3267, 0), new Tile(3210, 3267, 0),
			new Tile(3208, 3262, 0), new Tile(3211, 3258, 0) });

	public static final Area FEATHER_AREA = new Area(new Tile[] {
			new Tile(3223, 3303, 0), new Tile(3228, 3302, 0),
			new Tile(3233, 3302, 0), new Tile(3238, 3299, 0),
			new Tile(3237, 3294, 0), new Tile(3237, 3289, 0),
			new Tile(3236, 3284, 0), new Tile(3231, 3284, 0),
			new Tile(3226, 3286, 0), new Tile(3223, 3290, 0),
			new Tile(3222, 3285, 0) });

	public static final Area UNICORN_AREA = new Area(new Tile[] {
			new Tile(3271, 3374, 0), new Tile(3277, 3374, 0),
			new Tile(3282, 3374, 0), new Tile(3287, 3374, 0),
			new Tile(3292, 3373, 0), new Tile(3296, 3370, 0),
			new Tile(3301, 3368, 0), new Tile(3303, 3363, 0),
			new Tile(3302, 3358, 0), new Tile(3302, 3353, 0),
			new Tile(3301, 3348, 0), new Tile(3300, 3343, 0),
			new Tile(3297, 3338, 0), new Tile(3292, 3337, 0),
			new Tile(3287, 3335, 0), new Tile(3282, 3335, 0),
			new Tile(3277, 3336, 0), new Tile(3272, 3338, 0),
			new Tile(3267, 3339, 0), new Tile(3263, 3342, 0),
			new Tile(3259, 3345, 0), new Tile(3257, 3350, 0),
			new Tile(3254, 3355, 0), new Tile(3253, 3360, 0),
			new Tile(3252, 3365, 0), new Tile(3252, 3370, 0) });

	public static final Area HORN_BANK = new Area(new Tile[] {
			new Tile(3255, 3419, 0), new Tile(3258, 3423, 0),
			new Tile(3258, 3428, 0), new Tile(3253, 3429, 0),
			new Tile(3248, 3429, 0), new Tile(3246, 3424, 0),
			new Tile(3245, 3419, 0), new Tile(3248, 3415, 0),
			new Tile(3253, 3415, 0), new Tile(3258, 3414, 0),
			new Tile(3259, 3419, 0), new Tile(3258, 3424, 0) });

	// Paths
	public static final Tile[] FLAX_TO_BANK = new Tile[] {
			new Tile(2741, 3443, 0), new Tile(2736, 3442, 0),
			new Tile(2731, 3444, 0), new Tile(2730, 3449, 0),
			new Tile(2729, 3454, 0), new Tile(2728, 3459, 0),
			new Tile(2728, 3464, 0), new Tile(2729, 3469, 0),
			new Tile(2728, 3474, 0), new Tile(2727, 3479, 0),
			new Tile(2723, 3482, 0), new Tile(2725, 3487, 0) };

	public static final Tile[] BANK_TO_FLAX = new Tile[] {
			new Tile(2725, 3490, 0), new Tile(2725, 3485, 0),
			new Tile(2725, 3480, 0), new Tile(2725, 3475, 0),
			new Tile(2724, 3470, 0), new Tile(2724, 3465, 0),
			new Tile(2725, 3460, 0), new Tile(2727, 3455, 0),
			new Tile(2730, 3450, 0), new Tile(2732, 3445, 0),
			new Tile(2737, 3443, 0) };

	public static final Tile[] BANK_TO_SPINNER = new Tile[] {
			new Tile(2725, 3489, 0), new Tile(2724, 3484, 0),
			new Tile(2723, 3479, 0), new Tile(2720, 3475, 0),
			new Tile(2716, 3472, 0), new Tile(2712, 3470, 0) };

	public static final Tile[] SPINNER_TO_BANK = new Tile[] {
			new Tile(2712, 3471, 0), new Tile(2717, 3471, 0),
			new Tile(2722, 3472, 0), new Tile(2722, 3477, 0),
			new Tile(2722, 3482, 0), new Tile(2725, 3486, 0),
			new Tile(2724, 3490, 0), new Tile(2727, 3491, 0) };

	public static final Tile[] GLITCH_TO_BANK = new Tile[] {
			new Tile(2738, 3502, 0), new Tile(2739, 3497, 0),
			new Tile(2738, 3492, 0), new Tile(2735, 3488, 0),
			new Tile(2731, 3485, 0), new Tile(2726, 3485, 0),
			new Tile(2724, 3490, 0) };

	public static final Tile[] BOX_TO_BONES = new Tile[] {
			new Tile(3214, 3259, 0), new Tile(3219, 3259, 0),
			new Tile(3224, 3260, 0), new Tile(3229, 3261, 0),
			new Tile(3234, 3262, 0), new Tile(3239, 3262, 0),
			new Tile(3244, 3262, 0), new Tile(3247, 3258, 0),
			new Tile(3251, 3255, 0), new Tile(3254, 3251, 0),
			new Tile(3256, 3246, 0), new Tile(3253, 3242, 0),
			new Tile(3250, 3238, 0) };

	public static final Tile[] BONES_TO_BOX = new Tile[] {
			new Tile(3252, 3242, 0), new Tile(3255, 3246, 0),
			new Tile(3252, 3250, 0), new Tile(3250, 3255, 0),
			new Tile(3248, 3260, 0), new Tile(3243, 3261, 0),
			new Tile(3238, 3262, 0), new Tile(3233, 3262, 0),
			new Tile(3228, 3262, 0), new Tile(3223, 3261, 0),
			new Tile(3218, 3261, 0), new Tile(3213, 3260, 0) };

	public static final Tile[] DEPOSITBOX_TO_HIDES = new Tile[] {
			new Tile(3213, 3262, 0), new Tile(3218, 3261, 0),
			new Tile(3223, 3261, 0), new Tile(3228, 3261, 0),
			new Tile(3233, 3262, 0), new Tile(3238, 3263, 0),
			new Tile(3243, 3263, 0), new Tile(3248, 3264, 0),
			new Tile(3252, 3267, 0), new Tile(3254, 3268, 0) };

	public static final Tile[] HIDES_TO_DEPOSITBOX = new Tile[] {
			new Tile(3254, 3291, 0), new Tile(3256, 3286, 0),
			new Tile(3258, 3281, 0), new Tile(3259, 3276, 0),
			new Tile(3258, 3271, 0), new Tile(3256, 3266, 0),
			new Tile(3251, 3266, 0), new Tile(3247, 3263, 0),
			new Tile(3242, 3262, 0), new Tile(3237, 3262, 0),
			new Tile(3232, 3262, 0), new Tile(3227, 3261, 0),
			new Tile(3222, 3260, 0), new Tile(3217, 3260, 0),
			new Tile(3212, 3261, 0) };

	public static final Tile[] ANTI_BAN_WALK = new Tile[] {
			new Tile(3237, 3284, 0), new Tile(3240, 3280, 0),
			new Tile(3241, 3275, 0), new Tile(3243, 3270, 0),
			new Tile(3245, 3265, 0), new Tile(3249, 3262, 0),
			new Tile(3252, 3266, 0), new Tile(3257, 3268, 0) };

	public static final Tile[] BANK_TO_UNICORNS = new Tile[] {
			new Tile(3252, 3421, 0), new Tile(3252, 3426, 0),
			new Tile(3256, 3429, 0), new Tile(3261, 3429, 0),
			new Tile(3266, 3429, 0), new Tile(3271, 3429, 0),
			new Tile(3276, 3428, 0), new Tile(3281, 3427, 0),
			new Tile(3284, 3423, 0), new Tile(3285, 3418, 0),
			new Tile(3286, 3413, 0), new Tile(3289, 3409, 0),
			new Tile(3291, 3404, 0), new Tile(3292, 3399, 0),
			new Tile(3291, 3394, 0), new Tile(3290, 3389, 0),
			new Tile(3291, 3384, 0), new Tile(3293, 3379, 0),
			new Tile(3298, 3377, 0), new Tile(3296, 3372, 0),
			new Tile(3292, 3369, 0), new Tile(3293, 3364, 0),
			new Tile(3296, 3360, 0), new Tile(3296, 3355, 0),
			new Tile(3292, 3352, 0), new Tile(3287, 3350, 0) };

	public static final Tile[] UNICORNS_TO_BANK = new Tile[] {
			new Tile(3286, 3351, 0), new Tile(3291, 3351, 0),
			new Tile(3296, 3352, 0), new Tile(3296, 3357, 0),
			new Tile(3296, 3362, 0), new Tile(3296, 3367, 0),
			new Tile(3296, 3372, 0), new Tile(3296, 3377, 0),
			new Tile(3295, 3382, 0), new Tile(3291, 3385, 0),
			new Tile(3290, 3390, 0), new Tile(3291, 3395, 0),
			new Tile(3291, 3400, 0), new Tile(3291, 3405, 0),
			new Tile(3291, 3410, 0), new Tile(3288, 3414, 0),
			new Tile(3285, 3418, 0), new Tile(3284, 3423, 0),
			new Tile(3281, 3427, 0), new Tile(3276, 3428, 0),
			new Tile(3271, 3428, 0), new Tile(3266, 3429, 0),
			new Tile(3261, 3428, 0), new Tile(3256, 3428, 0),
			new Tile(3253, 3424, 0), new Tile(3253, 3419, 0) };

	public static final Tile[] LUMBY_TO_UNICORNS = new Tile[] {
			new Tile(3220, 3217, 0), new Tile(3225, 3217, 0),
			new Tile(3230, 3217, 0), new Tile(3235, 3217, 0),
			new Tile(3236, 3222, 0), new Tile(3232, 3225, 0),
			new Tile(3230, 3230, 0), new Tile(3227, 3234, 0),
			new Tile(3224, 3238, 0), new Tile(3221, 3242, 0),
			new Tile(3220, 3247, 0), new Tile(3219, 3252, 0),
			new Tile(3218, 3257, 0), new Tile(3222, 3260, 0),
			new Tile(3227, 3260, 0), new Tile(3232, 3262, 0),
			new Tile(3237, 3262, 0), new Tile(3241, 3265, 0),
			new Tile(3241, 3270, 0), new Tile(3238, 3274, 0),
			new Tile(3237, 3279, 0), new Tile(3237, 3284, 0),
			new Tile(3238, 3289, 0), new Tile(3238, 3294, 0),
			new Tile(3239, 3299, 0), new Tile(3239, 3304, 0),
			new Tile(3242, 3308, 0), new Tile(3244, 3313, 0),
			new Tile(3248, 3316, 0), new Tile(3252, 3319, 0),
			new Tile(3257, 3321, 0), new Tile(3262, 3323, 0),
			new Tile(3266, 3326, 0), new Tile(3271, 3327, 0),
			new Tile(3274, 3331, 0), new Tile(3277, 3335, 0),
			new Tile(3278, 3340, 0), new Tile(3279, 3345, 0),
			new Tile(3281, 3350, 0) };

	public static final Tile[] BONE_COW_FIX = new Tile[] {
			new Tile(3257, 3258, 0), new Tile(3257, 3263, 0),
			new Tile(3254, 3267, 0), new Tile(3250, 3264, 0),
			new Tile(3249, 3259, 0), new Tile(3250, 3254, 0),
			new Tile(3253, 3250, 0), new Tile(3252, 3245, 0) };

	public static final Tile[] FEATHER_TO_BANK = new Tile[] {
			new Tile(3232, 3293, 0), new Tile(3236, 3296, 0),
			new Tile(3238, 3291, 0), new Tile(3237, 3286, 0),
			new Tile(3238, 3281, 0), new Tile(3242, 3278, 0),
			new Tile(3245, 3274, 0), new Tile(3248, 3270, 0),
			new Tile(3249, 3267, 0), new Tile(3249, 3262, 0),
			new Tile(3244, 3261, 0), new Tile(3239, 3262, 0),
			new Tile(3234, 3262, 0), new Tile(3229, 3262, 0),
			new Tile(3224, 3261, 0), new Tile(3219, 3261, 0),
			new Tile(3214, 3260, 0), new Tile(3212, 3260, 0) };

	public static final Tile[] BANK_TO_FEATHERS = new Tile[] {
			new Tile(3213, 3258, 0), new Tile(3218, 3259, 0),
			new Tile(3223, 3260, 0), new Tile(3228, 3261, 0),
			new Tile(3233, 3262, 0), new Tile(3238, 3261, 0),
			new Tile(3243, 3261, 0), new Tile(3248, 3263, 0),
			new Tile(3249, 3268, 0), new Tile(3245, 3271, 0),
			new Tile(3243, 3276, 0), new Tile(3239, 3279, 0),
			new Tile(3236, 3283, 0), new Tile(3237, 3288, 0),
			new Tile(3239, 3293, 0), new Tile(3235, 3296, 0) };

	static final Area ftile = new Area(new Tile[] { new Tile(3159, 3491, 0),
			new Tile(3159, 39492, 0) });
}