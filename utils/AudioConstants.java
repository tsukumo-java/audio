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

package tsu.audio.utils;

/**
 * AudioConstants - holds all constants needed by audio components.
 *
 * @author Christopher Kelley
 * @version 1
 * @since 2014.06
 */
public interface AudioConstants
{
    // sampling rate
    final int SAMPLE_RATE           = 44100;

    // buffer size
    final int SAMPLE_BUFFER_SIZE    = 4096;

    // byte sampling info for 16-bit audio
    final int BYTES_PER_SAMPLE      = 2;
    final int BITS_PER_SAMPLE       = BYTES_PER_SAMPLE * 8;
    final double MAX_16_BIT         = Short.MAX_VALUE;
}
