# Banking SaaS Platform - API Contract v1

## Overview

This document defines the API contract for the Multi-Tenant Banking SaaS Platform.

### Roles

- SUPER_ADMIN
- ADMIN
- CUSTOMER

### Base URL

```http
/api
```

### Authentication

JWT Token Based Authentication

Authorization Header:

```http
Authorization: Bearer <jwt-token>
```

---

# Authentication Module

## Register User

### Endpoint

```http
POST /api/auth/register
```

### Request

```json
{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "password123",
  "role": "CUSTOMER",
  "tenantId": 1
}
```

### Response

```json
{
  "message": "User registered successfully"
}
```

### Status Codes

| Code | Description         |
| ---- | ------------------- |
| 201  | Created             |
| 400  | Bad Request         |
| 409  | User Already Exists |

---

## Login

### Endpoint

```http
POST /api/auth/login
```

### Request

```json
{
  "email": "john@example.com",
  "password": "password123"
}
```

### Response

```json
{
  "token": "jwt-token",
  "role": "CUSTOMER",
  "tenantId": 1,
  "userId": 101
}
```

### Status Codes

| Code | Description         |
| ---- | ------------------- |
| 200  | Success             |
| 401  | Invalid Credentials |

---

## Get Current User

### Endpoint

```http
GET /api/auth/me
```

### Response

```json
{
  "userId": 101,
  "name": "John Doe",
  "email": "john@example.com",
  "role": "CUSTOMER",
  "tenantId": 1
}
```

---

# Tenant Module

## Create Tenant

### Endpoint

```http
POST /api/tenants
```

### Access

SUPER_ADMIN

### Request

```json
{
  "bankName": "ABC Bank",
  "logoUrl": "abc-logo.png"
}
```

### Response

```json
{
  "tenantId": 1,
  "bankName": "ABC Bank"
}
```

---

## Get All Tenants

### Endpoint

```http
GET /api/tenants
```

### Access

SUPER_ADMIN

### Response

```json
[
  {
    "tenantId": 1,
    "bankName": "ABC Bank"
  }
]
```

---

## Get Tenant By Id

### Endpoint

```http
GET /api/tenants/{id}
```

---

# User Module

## Get Profile

### Endpoint

```http
GET /api/users/profile
```

### Access

CUSTOMER / ADMIN

### Response

```json
{
  "userId": 101,
  "name": "John Doe",
  "email": "john@example.com",
  "role": "CUSTOMER"
}
```

---

## Get All Users

### Endpoint

```http
GET /api/users
```

### Access

ADMIN

### Response

```json
[
  {
    "userId": 101,
    "name": "John Doe",
    "email": "john@example.com"
  }
]
```

---

# Account Module

## Get My Accounts

### Endpoint

```http
GET /api/accounts
```

### Access

CUSTOMER

### Response

```json
[
  {
    "accountId": 1,
    "accountType": "SAVINGS",
    "balance": 50000
  }
]
```

---

## Get Account Details

### Endpoint

```http
GET /api/accounts/{id}
```

### Access

CUSTOMER

### Response

```json
{
  "accountId": 1,
  "accountType": "SAVINGS",
  "balance": 50000
}
```

---

# Transaction Module

## Get Transactions

### Endpoint

```http
GET /api/transactions
```

### Access

CUSTOMER

### Response

```json
[
  {
    "transactionId": 1,
    "amount": 5000,
    "transactionType": "CREDIT",
    "transactionDate": "2026-06-01"
  }
]
```

---

## Get Transactions By Account

### Endpoint

```http
GET /api/transactions/account/{id}
```

### Access

CUSTOMER

---

# Loan Module

## Apply Loan

### Endpoint

```http
POST /api/loans
```

### Access

CUSTOMER

### Request

```json
{
  "amount": 100000,
  "purpose": "Education"
}
```

### Response

```json
{
  "loanId": 1,
  "status": "PENDING"
}
```

---

## Get My Loans

### Endpoint

```http
GET /api/loans/my
```

### Access

CUSTOMER

### Response

```json
[
  {
    "loanId": 1,
    "amount": 100000,
    "status": "PENDING"
  }
]
```

---

## Get All Loans

### Endpoint

```http
GET /api/loans
```

### Access

ADMIN

### Response

```json
[
  {
    "loanId": 1,
    "customerName": "John Doe",
    "amount": 100000,
    "status": "PENDING"
  }
]
```

---

## Approve Loan

### Endpoint

```http
PUT /api/loans/{id}/approve
```

### Access

ADMIN

### Response

```json
{
  "message": "Loan approved successfully"
}
```

---

## Reject Loan

### Endpoint

```http
PUT /api/loans/{id}/reject
```

### Access

ADMIN

### Response

```json
{
  "message": "Loan rejected successfully"
}
```

---

# Multi-Tenant Strategy

Every major table contains:

```text
tenant_id
```

Tables:

- tenants
- users
- accounts
- transactions
- loans

The tenantId will be extracted from the JWT token and used to filter records.

Example:

Bank A users can only view Bank A data.

Bank B users can only view Bank B data.

---

# Standard Error Response

```json
{
  "timestamp": "2026-06-01T10:30:00",
  "status": 400,
  "message": "Validation failed"
}
```

---

# HTTP Status Codes

| Code | Meaning               |
| ---- | --------------------- |
| 200  | Success               |
| 201  | Created               |
| 400  | Bad Request           |
| 401  | Unauthorized          |
| 403  | Forbidden             |
| 404  | Not Found             |
| 409  | Conflict              |
| 500  | Internal Server Error |

```

```

# API Contract
