public class Sound {


    // Master doc -- these things are a part of ALL classes, not just Sound.java. Keep that in mind.
    // =====================================================================================
    // timbre = difference in sound qualities between different instruments.
    // This goes into acoustics stuff

    // Same-model differences (subtle ones: makes each SAME-MODEL instrument unique like having a UUID)
    // - Pitch bending (affected by naturalImperfection value, and massively by artificialImperfection - which is things like dents and human whoopsies)
    // - Overtones
    // - Volume (very small differences)
    // - Calibration (uncalibrated = more SMDs).

    // Different-model differences
    // - Material type (affects SMDs)
    // - Material quality (inc nI value if low-quality.)
    // - Purposeful differences like different shape?

    // Player differences (changes regardless of what model is used.)
    // Force of air (How hard you blow affects SMDs)
    // Embouchure (massively affects sound quality).
    // Speed of air (fast/cold, slow/hot, etc)
    // Environment (temp affects pitch, crowded area lowers your embouchure quality, etc. Relies on time of day, weather, location.)
    // Amount of air (using circular breathing or stopping to take breaths -- latter causes abrupt surges and stops in air force; also, starting a new breath naturally has diff force/speed than after beginning to play, analogous to static vs kinetic fric)
    //    ** QUOTE: "Changes in air temp, pressure and humidity affect the final note produced... "exact characteristics of a digeridoo change with the day and player."
    // Distractions: see Musician.java/Simulation.java.

    // OPTIONAL: recording quality.
    // { Some playing can be saved (e.g. replayed) by enabling rec feature. But a rec will never have the same quality as raw playing. }
    // [factors.]


    // And the quality of a recording device has its own metric too.
    // - Recording quality: a numeric of how good the recording itself is, ignoring how close it is to raw playing.
    //    * The difference between RecQ and PRelQ is the idea that RQ determines the quality in terms of recordings alone, while PRQ determines quality INCLUDING raw playing too.
    // - Raw-Processed Disparity: how close the processed audio is to the raw audio, no matter the quality.
    //
    // - Processed Relative Timbre (see below.)
    // - Processed Relative Quality (see below.)

    // These are measured through metrics:
    // - Raw Relative Timbre: a numeric of your sound compared to other sounds. It is not a measure of quality of sound.
    // - Raw Relative Quality: a numeric of how good your sound is. It's on a scale of 1-100. This is based on how close your experimental pitch is the theoretical pitch.
    // - Absolute Timbre: timbre adjusted for environment and processing. A Brand A dig'doo played on foggy Wed morning vs. same dig'doo played at rainy afternoon has diff RT but same AT.
    // - Absolute Quality: quality adjusted for environment and processing. Environment can lower quality (too cold = impossible for perfect pitch) or increase it (more pressure = less need for perfect embouchure). AQ




    // Altogether these can all be articulated in a more human-readable format...
    // - Audiophile Metric: how likely an audiophile (someone who is knowledgable of the aspects of sound quality, and actively WANT a good sound) is to "enjoy" your sound. This applies to processed and raw sounds indiscriminately.
    // - Audiopleb Metric: how likely an audio pleb (someone who just wants to roughly hear the sound, doesn't know the specifics of sound quality, just 'hears it and bases it on if it sounds good'). Audioplebs generally accept lower qualities than audiophiles. HOWEVER, audiophiles, with their passion and appreciation for sound, may enjoy something that sounds bad to a regular audiopleb such as an innovative melody -- see CI.
    // - Creativity Index: how creative a particular sound or melody is. Higher = more likely an "edge case" occurs -- audiopleb hates, audiophile loves. In general increasing this will improve APHM and APLM.
    // - Sound Color: the color of your sound (this is like articulating something like the tone of playing as a color -- super subjective). "This playing feels like coffee brown with a tinge of white." Affected by CCS (below.)
    //      * Context Color Shift: playback format affects sound color (video has visual stimulus that can alter your color; e.g. a video with pastel bright 90s aesthetic will likely brighten the color versus only the audio rec). In general each time period has a general color shift (e.g. 1920s less colorful/blue b/c of Great Depression and natural retrospective view of 100+ years ago as black-and-white, 2010s more "tinged white" due to advent of modernist style of computers combined with globalization of computers.

    // And of course some obvious, matter-of-fact metrics. Only applies to recordings.
    // - Time: how long the audio clip is.
    // - File info: what type of file format, filesize. Really leaning into "it's a file on your computer and you're checking metadata", heheheh.
    // - Timestamp: when the audio was recorded. Naturally includes specific environmental details. (NOTE: raw playing will contain a few environmental details, but not many.)


}
