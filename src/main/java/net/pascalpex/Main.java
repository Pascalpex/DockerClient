package net.pascalpex;

import net.pascalpex.controller.NetworkManager;
import net.pascalpex.controller.TimeAggregator;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        NetworkManager networkManager = new NetworkManager();
        try {
            TimeAggregator aggregator = new TimeAggregator(networkManager.getDataset());
            boolean success = networkManager.postResults(aggregator.getTimes());
            if(success) {
                System.out.println("Erfolgreich");
            } else {
                System.out.println("Fehlgeschlagen");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}