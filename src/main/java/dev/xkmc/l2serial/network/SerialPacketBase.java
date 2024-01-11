package dev.xkmc.l2serial.network;

import dev.xkmc.l2serial.serialization.SerialClass;
import dev.xkmc.l2serial.serialization.codec.PacketCodec;
import net.minecraft.network.FriendlyByteBuf;

import java.util.Objects;
import java.util.function.Supplier;

@SerialClass
public abstract class SerialPacketBase extends SimplePacketBase {

	public static <T extends SerialPacketBase> T serial(Class<T> cls, FriendlyByteBuf buf) {
		return Objects.requireNonNull(PacketCodec.from(buf, cls, null));
	}

	@Override
	public final void write(FriendlyByteBuf buffer) {
		PacketCodec.to(buffer, this);
	}

	@Override
	public final void handle(Supplier<NetworkEvent.Context> context) {
		handle(context.get());
	}

	public abstract void handle(NetworkEvent.Context ctx);

}
