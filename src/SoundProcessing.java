import java.time.LocalDateTime;

public class SoundProcessing { // Recordings, playback, n' crap. Anything to do with audio that isn't raw.
    private double processedRelativeTimbre, processedRelativeQuality, recordingQuality, rawToProcessedDisparity;
    // private double... *** RECORDING DEVICE FACTORS GO HERE. THESE ARE INSTANCE VARIABLES.

    public class AudioFile {
        private LocalDateTime timeEdited, timeLastOpened, timestamp; // timestamp = timeCreated
        private String fileFormat;
        private double fileSizekb;
    }

}
