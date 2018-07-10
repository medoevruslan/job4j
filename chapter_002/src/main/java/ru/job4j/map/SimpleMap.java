package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public class SimpleMap<K, V> implements Iterable<SimpleMap.Element<K, V>> {
    private Element<K, V>[] table;
    private static final float LOAD_FACTOR = 0.75f;
    private int threshold = 12;
    private int size = 0;
    private int modCount = 0;

    public SimpleMap() {
        this.table = new Element[16];
    }

    public int getSize() {
        return this.size;
    }

    public boolean insert(K key, V value) {
        boolean rst = false;
        if (key != null) {
            int index = this.getIndex(key);
            if (this.table[index] == null) {
                this.table[index] = new Element<>(key, value);
                this.modCount++;
                rst = true;
                if (++this.size > this.threshold) {
                    this.resize(this.table);
                }
            }
        } else if (this.table[0] == null) {
            this.table[0] = new Element<>(key, value);
            this.modCount++;
            rst = true;
            if (++this.size > this.threshold) {
                this.resize(this.table);
            }
        }
        return rst;
    }

    private void resize(Element<K, V>[] table) {
        Element<K, V>[] newTable = new Element[this.table.length << 1];
        transfer(table, newTable);
        this.threshold = (int) (this.table.length * LOAD_FACTOR);
        this.modCount++;
    }

    private void transfer(Element<K, V>[] oldTable, Element<K, V>[] newTable) {
        int indx;
        for (Element el : oldTable) {
            if (el != null) {
                if (el.getKey() == null) {
                    newTable[0] = el;
                } else {
                    int hash = this.hash(el.getKey().hashCode());
                    indx = this.indexFor(hash, newTable.length);
                    newTable[indx] = el;
                }
            }
        }
        this.table = newTable;
    }

    public V get(K key) {
        V value = null;
        if (key == null && this.table[0] != null) {
            value = this.table[0].value;
        } else {
            int index = this.getIndex(key);
            if (this.table[index].getKey().equals(key)) {
                value = this.table[index].value;
            }
        }
        return value;
    }

    public boolean delete(K key) {
        boolean result = false;
        int index = this.getIndex(key);
        if (this.table[index] != null) {
            if (key == null && this.table[0] != null) {
                this.modCount++;
                this.table[0] = null;
                this.size--;
                result = true;
            } else {
                this.modCount++;
                if (this.table[index].getKey().equals(key)) {
                    this.table[index] = null;
                    this.size--;
                    result = true;
                }
            }
        }
        return result;
    }

    private int getIndex(K key) {
        return key == null ? 0 : indexFor(key.hashCode(), this.table.length);
    }

    private int hash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    private int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    @Override
    public Iterator<SimpleMap.Element<K, V>> iterator() {
        return new Iterator<SimpleMap.Element<K, V>>() {
            private int expectedModCount = modCount;
            private int position = 0;

            @Override
            public boolean hasNext() {
                boolean isTrue = false;
                for (int idx = position; idx <= size; idx++) {
                    if (table[idx] != null) {
                        isTrue = true;
                        break;
                    }
                }
                return isTrue;
            }

            @Override
            public SimpleMap.Element<K, V> next() {
                if (this.expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Element<K, V> el = null;
                for (; position <= size; position++) {
                    if (table[position] != null) {
                        el = table[position++];
                        break;
                    }
                }
                return el;
            }
        };
    }

    static class Element<K, V> {
        final K key;
        V value;

        public Element(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public int hashCode() {
            return Objects.hashCode(this.key) ^ Objects.hashCode(this.value);

        }

        public K getKey() {
            return this.key;
        }

        public V value() {
            return this.value;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o instanceof SimpleMap.Element) {
                SimpleMap.Element<?, ?> element = (SimpleMap.Element<?, ?>) o;
                if (this.key.equals(element.getKey())
                        && this.value.equals(element.value)) {
                    return true;
                }
            }
            return false;
        }
    }
}
