package com.tebreca.steammadness.helper.reflect;

import java.util.List;

public interface EntryHolder<T> {

    void runInjector();

    List<T> all();

}
