// ========================================================================== //
//                                 _      _/._                                //
//                                /_|/_//_///_/                               //
//                                                                            //
//                Christopher Kelley <tsukumo.code(at)gmail.com>              //
//                              Copyright (C) 2014                            //
//                                                                            //
//                               The MIT License                              //
//                                                                            //
// ========================================================================== //

package tsu.audio.manager;

import tsu.audio.utils.AudioConstants;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

/**
 * AudioManager - handles sending of samples to the system sound card.
 *
 * @author Christopher Kelley
 * @version 1
 * @since 2014.06
 */
public class AudioManager implements AudioConstants
{
    static private SourceDataLine m_line; //< to play sound
    static private byte[] m_buffer;       //< internal buffer
    static private int m_size;            //< number of samples currently
                                          //  in the buffer

    // static initializer
    static
    { init(); }

    private AudioManager()
    { }

    /**
     * Static initialization - open audio steam
     */
    static public void init()
    {
        try
        {
            // Create a format for the system
            AudioFormat format = new AudioFormat
            (
                (float) SAMPLE_RATE, // samples per second
                BITS_PER_SAMPLE,     // 16-bit audio
                1,                   // mono
                true,                // signed PCM
                false                // little endian
             );

            // Create a data line as a source data line with specified format
            DataLine.Info info = new DataLine.Info(
                SourceDataLine.class, format);

            // Store the audio system line for writing
            m_line = (SourceDataLine) AudioSystem.getLine(info);

            // Open the line for writing
            m_line.open(format, SAMPLE_BUFFER_SIZE * BYTES_PER_SAMPLE);

            // Initialize the buffer
            m_buffer = new byte[SAMPLE_BUFFER_SIZE * BYTES_PER_SAMPLE / 3];

            // Initialize the buffer size
            m_size = 0;

            // Start the audio track
            m_line.start();
        }
        catch (Exception e)
        {
            // TODO: logging?
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Closes the audio stream
     */
    static public void close()
    {
        m_line.drain();
        m_line.stop();
    }

    /**
     * Write a sample to the internal buffer and send to the sound card
     * when that buffer is full.
     *
     * @param in Sample to buffer.
     */
    static public void send(double in)
    {
        // Clip audio
        in = Math.max(-1.0, Math.min(in, 1.0));

        // Translate to bytes
        short s = (short) (MAX_16_BIT * in);

        m_buffer[m_size++] = (byte) s;        // trim first byte
        m_buffer[m_size++] = (byte) (s >> 8); // shift and trim second byte

        // Send to the sound card if the buffer is full
        if (m_size >= m_buffer.length)
        {
            m_line.write(m_buffer, 0, m_buffer.length);
            m_size = 0; // don't bother clearing, it will overwrite
        }
    }

    /**
     * Write samples to the internal buffer and send to the sound card
     * when that buffer is full.
     *
     * @param in Samples to buffer.
     */
    static public void send(double[] in)
    {
        for (double sample: in)
        {
            send(sample);
        }
    }

}

