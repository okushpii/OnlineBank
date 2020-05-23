package com.alexbro.onlinebank.facade.populator.util;

public interface Populator<SOURCE, TARGET> {

    void populate(SOURCE source, TARGET target);
}
