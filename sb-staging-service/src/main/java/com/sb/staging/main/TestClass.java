package com.sb.staging.main;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

public class TestClass {

    @Test
    public void test1() {
        System.out.println(clientSeqFromPolicyNum("G4EIS0015485"));
    }

    public long clientSeqFromPolicyNum(Object policyNumber) {
        if (!(policyNumber instanceof String)) {
            return 0;
        }
        String source = policyNumber.toString();
        int start = source.length() >= 9 ? source.length() - 9 : 0;

        String temp;
        boolean zeroSkipped = false;
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < source.length(); i++) {
            temp = source.substring(i, i + 1);
            if (StringUtils.isNumeric(temp)) {
                if ((!zeroSkipped) && "0".equals(temp)) {
                    continue;
                }
                zeroSkipped = true;
                sb.append(temp);
            }
        }

        return sb.length() == 0 ? 0L : Long.parseLong(sb.toString());
    }
}
