package com.simonmittag.loggingutils;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Current CPU percentage wrapper object for print
 */
public class CPUPercentage {
    protected static final String USED_CPUPERCENT = "usedCPUPercent=";
    protected NumberFormat nf;
    protected float consumed;

    public CPUPercentage(float consumed) {
        this.consumed = consumed;
        this.nf = new DecimalFormat();
        nf.setGroupingUsed(false);
        nf.setMaximumFractionDigits(0);
        nf.setMinimumFractionDigits(0);
        nf.setMinimumIntegerDigits(2);
        nf.setMaximumIntegerDigits(2);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(USED_CPUPERCENT);
        sb.append(nf.format(consumed * 100));
        return sb.toString();
    }
}
