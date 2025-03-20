package com.sb.staging.verify;

public abstract class DefaultLedgerAccount {
    public static final DefaultLedgerAccount ASSET = new DefaultLedgerAccount() {
        @Override
        public void process() {
            System.out.println("Processing Asset account");
        }
    };

    public static final DefaultLedgerAccount LIABILITY = new DefaultLedgerAccount() {
        @Override
        public void process() {
            System.out.println("Processing Liability account");
        }
    };

    // 抽象方法，子类必须实现
    public abstract void process();
}

