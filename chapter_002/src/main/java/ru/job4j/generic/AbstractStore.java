package ru.job4j.generic;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public abstract class AbstractStore<T extends Base> implements Store<T> {
    private SimpleArray<T> array;

    public AbstractStore(int size) {
        this.array = new SimpleArray<>(size);
    }

    public void add(T model) {
        this.array.add(model);
    }

    public boolean replace(String id, T model) {
        boolean result = false;
        for (T obj : this.array) {
            if (id.equals(obj.getId())) {
                result = true;
                this.array.set(this.array.indexOf(obj), model);
                break;
            }
        }
        return result;
    }

    public boolean delete(String id) {
        boolean result = false;
        for (T obj : this.array) {
            if (id.equals(obj.getId())) {
                result = true;
                this.array.delete(this.array.indexOf(obj));
            }
        }
        return result;
    }

    public T findById(String id) {
        T object = null;
        for (T obj : this.array) {
            if (id.equals(obj.getId())) {
                object = obj;
            }
        }
        return object;
    }
}
