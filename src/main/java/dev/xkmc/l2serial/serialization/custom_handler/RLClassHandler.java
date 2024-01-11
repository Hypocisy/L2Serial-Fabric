package dev.xkmc.l2serial.serialization.custom_handler;

import com.google.gson.JsonNull;
import com.google.gson.JsonPrimitive;
import net.minecraft.core.Registry;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;

import java.util.Objects;
import java.util.function.Supplier;

public class RLClassHandler<R extends Tag, T> extends ClassHandler<R, T> {

	public RLClassHandler(Class<T> cls, Supplier<Registry<T>> r) {
		super(cls, e -> e == null ? JsonNull.INSTANCE : new JsonPrimitive(Objects.requireNonNull(r.get().getKey(e)).toString()),
				e -> e.isJsonNull() ? null : r.get().get(new ResourceLocation(e.getAsString())),
				p -> {
					int index = p.readInt();
					if (index == -1)
						return null;
					return r.get().byId(index);
				},
				(p, t) -> p.writeInt(t == null ? -1 : r.get().getId(t)),
				s -> s.getAsString().isEmpty() ? null : r.get().get(new ResourceLocation(s.getAsString())),
				t -> t == null ? StringTag.valueOf("") : StringTag.valueOf(Objects.requireNonNull(r.get().getKey(t)).toString()));
	}

}
