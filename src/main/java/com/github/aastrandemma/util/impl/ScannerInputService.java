package com.github.aastrandemma.util.impl;

import com.github.aastrandemma.util.UserInputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ScannerInputService implements UserInputService {
    private Scanner scanner;

    @Autowired
    public ScannerInputService(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String getString() {
        if (scanner == null) throw new IllegalArgumentException("Input cannot be null");
        return scanner.nextLine();
    }

    @Override
    public int getInt() {
        if (scanner == null) throw new IllegalArgumentException("Input cannot be null");
        return scanner.nextInt();
    }
}
