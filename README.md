# TxView - Transaction Analysis Web Application

Web application for reviewing and analyzing transactions loaded by [txloader](../txloader). Reads from the SQLite database produced by the txloader ingestion pipeline.

**Architecture:** Domain APIs → BFF → React web frontend

## Modules

| Module | Port | Description |
|--------|------|-------------|
| `accounts-api` | — | Maven library — accounts entity, repository, controller |
| `transactions-api` | — | Maven library — transactions entity, repository, controller |
| `categories-api` | — | Maven library — category aggregation repository, controller |
| `domain-api` | 8082 | Spring Boot executable — packages the three domain modules together |
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
# Domain API (modular monolith — accounts, transactions, categories)
java -jar domain-api/target/domain-api-1.0-SNAPSHOT.jar

# BFF
java -jar bff/target/bff-1.0-SNAPSHOT.jar

# Web (dev)
cd web && npm run dev
```

Open **http://localhost:5173** in a browser.

## API Overview

| Endpoint | Service | Description |
|----------|---------|-------------|
| `GET /accounts` | domain-api | List all accounts |
| `GET /accounts/{id}` | domain-api | Get account by ID |
| `GET /transactions` | domain-api | List all transactions |
| `GET /transactions?accountId={id}` | domain-api | Filter by account |
| `GET /transactions?category={name}` | domain-api | Filter by category |
| `GET /categories` | domain-api | Spending totals by category |
| `GET /api/dashboard` | bff | Accounts + category summary |
| `GET /api/accounts` | bff | Accounts (via BFF) |
| `GET /api/accounts/{id}/transactions` | bff | Transactions for an account |
| `GET /api/categories` | bff | Category summary (via BFF) |
