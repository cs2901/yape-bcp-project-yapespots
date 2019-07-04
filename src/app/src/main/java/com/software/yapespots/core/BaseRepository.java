package com.software.yapespots.core;

import java.util.Collection;

public interface BaseRepository<T, Tid> {
    Collection<T> all();

    boolean create(T element);

    boolean update(T element, Tid id);

    boolean delete(Tid id);

    boolean find(Tid id);
}
