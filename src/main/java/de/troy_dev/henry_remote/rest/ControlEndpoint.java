package de.troy_dev.henry_remote.rest;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.troy_dev.henry_remote.HenrySocketHandler;
import de.troy_dev.henry_remote.Movement;

@RestController
@RequestMapping("/henry/{key}")
public class ControlEndpoint {

    private final HenrySocketHandler handler;

    @Autowired
    public ControlEndpoint(HenrySocketHandler handler) {
        this.handler = handler;
    }

    @RequestMapping(value = "/forward", method = RequestMethod.GET)
    public boolean forward(@PathVariable("key") String key) throws IOException {
        handler.broadcastMessage(key + "/forward");
        return true;
    }

    @RequestMapping(value = "/backward", method = RequestMethod.GET)
    public boolean backward(@PathVariable("key") String key) throws IOException {
        handler.broadcastMessage(key + "/backward");
        return true;
    }

    @RequestMapping(value = "/right", method = RequestMethod.GET)
    public boolean right(@PathVariable("key") String key) throws IOException {
        handler.broadcastMessage(key + "/right");
        return true;
    }

    @RequestMapping(value = "/left", method = RequestMethod.GET)
    public boolean left(@PathVariable("key") String key) throws IOException {
        handler.broadcastMessage(key + "/left");
        return true;
    }

    @RequestMapping(value = "/toggle", method = RequestMethod.GET)
    public boolean activateSensorRays(@PathVariable("key") String key) throws IOException {
        handler.broadcastMessage(key + "/toggle");
        return true;
    }

    @RequestMapping(value = "/sensor", method = RequestMethod.GET)
    public List<Integer> sensorData(@PathVariable("key") String key) {
        return handler.getSensorDistances().get(key);
    }

    @RequestMapping(value = "/movement", method = RequestMethod.GET)
    public Movement movement(@PathVariable("key") String key) {
        return new Movement(handler.getWalkModes().get(key), handler.getTurnModes().get(key));
    }

    @RequestMapping(value = "/walk/{mode}", method = RequestMethod.GET)
    public boolean walk(@PathVariable("key") String key, @PathVariable("mode") String mode) throws IOException {
        handler.broadcastMessage(key + "/walk/" + mode);
        return true;
    }

    @RequestMapping(value = "/turn/{mode}", method = RequestMethod.GET)
    public boolean turn(@PathVariable("key") String key, @PathVariable("mode") String mode) throws IOException {
        handler.broadcastMessage(key + "/turn/" + mode);
        return true;
    }
}
