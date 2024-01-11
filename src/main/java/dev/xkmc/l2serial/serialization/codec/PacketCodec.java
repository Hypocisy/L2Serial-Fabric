package dev.xkmc.l2serial.serialization.codec;

import dev.xkmc.l2serial.serialization.type_cache.TypeInfo;
import dev.xkmc.l2serial.serialization.unified_processor.PacketContext;
import dev.xkmc.l2serial.serialization.unified_processor.UnifiedCodec;
import dev.xkmc.l2serial.util.Wrappers;
import net.minecraft.network.FriendlyByteBuf;

import javax.annotation.Nullable;

public class PacketCodec {

	/**
	 * Deserialize an object from given data stream.
	 * @param buf The input data buffer to read from
	 * @param cls The deserialization type information
	 * @param ans The object to inject into. Constructs a new one if it's <code>null</code>
	 * @return The deserialized object. It will be the same as <code>ans</code> if it's a <code>@SerialClass</code> object.
	 * */
	@Nullable
	@SuppressWarnings("unchecked")
	public static <T> T from(FriendlyByteBuf buf, Class<T> cls, @Nullable T ans) {
		return Wrappers.get(() -> (T) UnifiedCodec.deserializeValue(new PacketContext(buf), buf, TypeInfo.of(cls), ans));
	}

	/**
	 * Serialize an object to data stream
	 * @param buf The output data buffer to write to
	 * @param obj The object to serialize
	 * */
	public static <T> void to(FriendlyByteBuf buf, T obj) {
		PacketCodec.to(buf, obj, Wrappers.cast(obj.getClass()));
	}

	/**
	 * Serialize an object to data stream
	 * @param buf The output data buffer to write to
	 * @param obj The object to serialize
	 * @param r The serialization type information
	 * */
	public static <T extends R, R> void to(FriendlyByteBuf buf, T obj, Class<R> r) {
		Wrappers.run(() -> UnifiedCodec.serializeValue(new PacketContext(buf), TypeInfo.of(r), obj));
	}

}
