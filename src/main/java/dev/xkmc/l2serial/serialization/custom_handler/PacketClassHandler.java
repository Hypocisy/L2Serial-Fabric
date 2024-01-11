package dev.xkmc.l2serial.serialization.custom_handler;

import net.minecraft.network.FriendlyByteBuf;

public interface PacketClassHandler<T> {

	void toPacket(FriendlyByteBuf buf, Object obj);

	T fromPacket(FriendlyByteBuf buf);
}
