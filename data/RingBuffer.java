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

package tsu.audio.data;

/**
 * RingBuffer - data structure needed for repetitious sound processing.
 *
 * @author Christopher Kelley
 * @version 1
 * @since 2014.06
 */
public class RingBuffer
{
    private int first;      //< First element position in the buffer
    private int size;       //< Size of the current buffer
                            //  Possibly different from the length of the buffer

    private double[] ring;  //< Array to hold the buffer information

    /**
     * Ctor.
     *
     * @param cap Capacity/length of the ring buffer.
     */
    public RingBuffer(int cap)
    {
        ring = new double[cap];
    }

    /**
     * Accessor method for the current size of the buffer.
     *
     * @return size of the current buffer.
     */
    public int size()
    {
        return size;
    }

    /**
     * Checks if the buffer is full.
     *
     * @return if the buffer is full.
     */
    public boolean isFull()
    {
        return size == ring.length;
    }

    /**
     * Checks if the buffer is empty.
     *
     * @return if the buffer is empty.
     */
    public boolean isEmpty()
    {
        return size == 0;
    }

    /**
     * Add a new item to the buffer.
     *
     * @param val Value to enqueue.
     */
    public void push_back(double val)
    {
        if (isFull())
        {
            throw new RingBufferException("overflow - push");
        }

        size++;
        ring[(first + size - 1) % ring.length] = val;
    }

    /**
     * Remove an item from the buffer.
     *
     * @return the item that was removed.
     */
    public double pop()
    {
        if (isEmpty())
        {
            throw new RingBufferException("underflow - pop");
        }

        size--;
        int tmp = first;
        first = (first + 1) % ring.length;
        return ring[tmp];
    }

    /**
     * Get the item from the front of the buffer without removing it.
     *
     * @return the item at the front of the buffer.
     */
    public double peek()
    {
        if (isEmpty())
        {
            throw new RingBufferException("underflow - peek");
        }

        return ring[first];
    }

    /**
     * Overrides the traditional toString to provide helpful output.
     *
     * @return string representation of the current object.
     */
    @Override
    public String toString()
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append("RingBuffer=[");
        for (double val : ring)
        {
            buffer.append(val).append(',');
        }
        buffer.deleteCharAt(buffer.length() - 1);
        buffer.append(']');
        return buffer.toString();
    }

    /**
     * RingBufferException - thrown at under/overflow of the ring buffer.
     */
    class RingBufferException extends RuntimeException
    {
        /**
         * Ctor.
         */
        RingBufferException()
        { }

        /**
         * Ctor.
         *
         * @param what Detail of the exception.
         */
        RingBufferException(String what)
        {
            super(what);
        }
    }
}
