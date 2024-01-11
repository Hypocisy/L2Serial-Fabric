package dev.xkmc.l2serial.network;

import net.minecraft.network.FriendlyByteBuf;


public abstract class SimplePacketBase {

	public abstract void write(FriendlyByteBuf buffer);

	public abstract void handle(Supplier<Context> context);

}
