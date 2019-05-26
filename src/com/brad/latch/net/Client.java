package com.brad.latch.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {

    public enum Error {
        NONE, INVALID_HOST, SOCKET_EXCEPTION
    }

    private String ipAddress;  // Server IP address
    private int port;          // server port
    private Error errorCode = Error.NONE;

    private InetAddress serverAddress;

    private DatagramSocket socket;

    /**
     * Create a client with an implicit connection
     * to the server at the given host name, where the port
     * is included as part of the host name. This host name
     * is NOT the IP address and port of the client, rather it is
     * the IP address and port of the server.
     * @param host  Format: 192.168.1.1:5000
     */
    public Client(String host) {
        String[] parts = host.split(":");
        if (parts.length != 2) {
            errorCode = Error.INVALID_HOST;
            return;
        }
        ipAddress = parts[0];
        try {
            port = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            errorCode = Error.INVALID_HOST;
            return;
        }

    }

    /**
     * Create a client with an implicit connection
     * to the server at the given host name, where the
     * IP address and port are separated. This host name
     * is NOT the IP address and port of the client, rather it
     * is the IP address and port of the server.
     * to the server at the
     * @param host  Format: 192.168.1.1
     * @param port  Format: 5000
     */
    public Client(String host, int port) {
        this.ipAddress = host;
        this.port = port;
    }

    public boolean connect() {
        try {
            serverAddress = InetAddress.getByName(ipAddress);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            errorCode = Error.INVALID_HOST;
            return false;
        }

        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
            errorCode = Error.SOCKET_EXCEPTION;
            return false;
        }

        sendConnectionPacket();
        // Wait for server to reply
        return true;
    }

    private void sendConnectionPacket() {
        byte[] data = "ConnectionPacket".getBytes();
        send(data);
    }

    @SuppressWarnings("Duplicates")
    public void send(byte[] data) {
        assert(socket.isConnected());
        DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress, port);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Error getErrorCode() {
        return errorCode;
    }
}
