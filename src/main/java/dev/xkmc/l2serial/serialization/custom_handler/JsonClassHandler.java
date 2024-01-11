package dev.xkmc.l2serial.serialization.custom_handler;

import com.google.gson.JsonElement;

public interface JsonClassHandler<T> {

	JsonElement toJson(Object obj);

	T fromJson(JsonElement e);
}
