package dev.xkmc.l2serial.serialization.codec;

import javax.annotation.Nullable;
import java.util.List;

public interface AliasCollection<T> {

	List<T> getAsList();

	void clear();

	void set(int n, int i, @Nullable T elem);

	Class<T> getElemClass();

	@SuppressWarnings({"unchecked", "unsafe"})
	default void setRaw(int n, int i, @Nullable Object elem) {
		set(n, i, (T) elem);
	}

}
