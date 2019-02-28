package de.troy_dev.henry_remote;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class HenrySocketHandler extends TextWebSocketHandler {
    private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    private final Map<String, List<Integer>> sensorDistances = new HashMap<>();
    private final Map<String, Integer> walkModes = new HashMap<>();
    private final Map<String, Integer> turnModes = new HashMap<>();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        String[] result = message.getPayload().split("/");
        String key = result[0];
        String walkMode = result[1];
        String turnMode = result[2];
        String[] values = result[3].split(",");
        walkModes.put(key, Integer.parseInt(walkMode));
        turnModes.put(key, Integer.parseInt(turnMode));
        sensorDistances.put(key, Arrays.stream(values).map(Integer::parseInt).collect(Collectors.toList()));
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        //the messages will be broadcasted to all users.
        sessions.add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        sessions.remove(session);
    }

    public int getSessionCount() {
        return sessions.size();
    }

    public void broadcastMessage(String message) throws IOException {
        for (WebSocketSession session : sessions) {
            session.sendMessage(new TextMessage(message));
        }
    }

    public Map<String, List<Integer>> getSensorDistances() {
        return sensorDistances;
    }

    public Map<String, Integer> getWalkModes() {
        return walkModes;
    }

    public Map<String, Integer> getTurnModes() {
        return turnModes;
    }
}
