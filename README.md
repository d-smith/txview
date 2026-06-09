# TxView - Transaction Analysis Web Application

Web application for reviewing and analyzing transactions loaded by [txloader](../txloader). Reads from the SQLite database produced by the txloader ingestion pipeline.

**Architecture:** Domain APIs → BFF → React web frontend

## Modules

| Module | Port | Description |
|--------|------|-------------|
| `accounts-api` | 8082 | REST API for account data |
| `transactions-api` | 8083 | REST API for transaction data |
| `categories-api` | 8084 | Aggregated spend by category |
| `bff` | 8080 | Backend-for-frontend — aggregates domain APIs for the web app |
| `web` | 5173 | React + Vite frontend (dev server) |

## Prerequisites

| Requirement | Notes |
|-------------|-------|
| Java 17+ | Required to run the JARs |
| Maven 3.8+ | Required to build from source |
| Node.js 20+ | Required for the web frontend |
| txloader database | The `transactions.db` file produced by txloader |

## Build

**Java modules:**
```bash
mvn package
```

**Web frontend:**
```bash
cd web && npm install && npm run build
```

## Running

Point all services at the txloader database by setting `DB_PATH`:

```bash
export DB_PATH=/path/to/transactions.db
```

Start services in any order:

```bash
# Domain APIs
java -jar accounts-api/target/accounts-api-1.0-SNAPSHOT.jar
java -jar transactions-api/target/transactions-api-1.0-SNAPSHOT.jar
java -jar categories-api/target/categories-api-1.0-SNAPSHOT.jar

# BFF
java -jar bff/target/bff-1.0-SNAPSHOT.jar

# Web (dev)
cd web && npm run dev
```

Open **http://localhost:5173** in a browser.

## API Overview

| Endpoint | Service | Description |
|----------|---------|-------------|
| `GET /accounts` | accounts-api | List all accounts |
| `GET /accounts/{id}` | accounts-api | Get account by ID |
| `GET /transactions` | transactions-api | List all transactions |
| `GET /transactions?accountId={id}` | transactions-api | Filter by account |
| `GET /transactions?category={name}` | transactions-api | Filter by category |
| `GET /categories` | categories-api | Spending totals by category |
| `GET /api/dashboard` | bff | Accounts + category summary |
| `GET /api/accounts` | bff | Accounts (via BFF) |
| `GET /api/accounts/{id}/transactions` | bff | Transactions for an account |
| `GET /api/categories` | bff | Category summary (via BFF) |
