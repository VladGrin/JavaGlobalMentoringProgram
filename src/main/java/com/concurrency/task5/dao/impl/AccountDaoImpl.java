package com.concurrency.task5.dao.impl;

import com.concurrency.task5.dao.AccountDao;
import com.concurrency.task5.util.AccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AccountDaoImpl implements AccountDao {

    private static final Logger LOG = LoggerFactory.getLogger(AccountDaoImpl.class);

    @Override
    public void save(String file, List<String> currencyValues) throws AccountException {
        Path path = Paths.get(file);
        try {
            Files.write(path, currencyValues);
            LOG.info("Currency values {} saved in file {}", currencyValues, file);
        } catch (IOException e) {
            LOG.error("Can not save data in file {}. File not exist or unavailable.", file);
            throw new AccountException("Can not save data in file " + file);
        }
    }

    @Override
    public List<String> findAllCurrencyValuesByAccount(String file) throws AccountException {
        Path path = Paths.get(file);
        try {
            List<String> values = Files.readAllLines(path);
            LOG.info("Currency values {} by file {}", values, path);
            return values;
        } catch (IOException e) {
            LOG.error("Can not find currency values by account {}. File not exist or unavailable.", file);
            throw new AccountException("Can not find currency values by account " + file);
        }
    }

    @Override
    public List<String> getAllAccounts() throws AccountException {
        try (Stream<Path> stream = Files.list(Paths.get(""))) {
            List<String> files = stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .filter(str -> str.startsWith("account") && str.endsWith(".txt"))
                    .collect(Collectors.toList());
            LOG.info("Found files: {}", files);
            return files;
        } catch (IOException e) {
            LOG.error("Can not get all accounts. File not exist or unavailable.");
            throw new AccountException("Can not get all accounts. File not exist or unavailable.");
        }
    }
}
