# QuickBite

A full-stack food ordering web app. Users can browse restaurants and menus, sign up and log in, add dishes to a cart, and check out.

- **Frontend:** React, TypeScript, Vite, Tailwind CSS, shadcn/ui-style components, TanStack Query
- **Backend:** Java 21, Spring Boot, Spring Security, Spring Data JDBC
- **Database:** PostgreSQL (AWS RDS in production, Docker for local development)

## Features

- Browse all restaurants and view each restaurant's menu
- Sign up, log in, and log out (session-based auth with Spring Security)
- Protected routes: cart, checkout, and order confirmation need a signed-in user
- Add items to the cart and check out
- Save favorite restaurants (stored in the browser)
- Server state is cached and kept in sync with TanStack Query
- Loading skeletons, empty states, and friendly error messages

## Architecture

```
React SPA (Vite)  ──HTTP/JSON──>  Spring Boot REST API  ──JDBC──>  PostgreSQL
  TanStack Query                    Spring Security                (AWS RDS)
  React Router                      Spring Data JDBC
                                    Caffeine cache
```

```
quickbite/
├── frontend/   # React + TypeScript single-page app
└── backend/    # Spring Boot REST API
```

## API

| Method | Endpoint | Auth | Description |
|---|---|---|---|
| POST | `/signup` | No | Create an account |
| POST | `/login` | No | Log in (form login, sets a session cookie) |
| POST | `/logout` | Yes | Log out |
| GET | `/restaurants/menu` | No | All restaurants with their menus |
| GET | `/restaurant/{id}/menu` | No | Menu for one restaurant |
| GET | `/cart` | Yes | Current user's cart |
| POST | `/cart` | Yes | Add a menu item to the cart |
| POST | `/cart/checkout` | Yes | Check out the cart |

## Run locally

### 1. Start PostgreSQL

```bash
cd backend
docker compose up -d
```

### 2. Start the backend (port 8080)

```bash
cd backend
./gradlew bootRun
```

The database connection is read from environment variables. The defaults match the local Docker database:

| Variable | Default |
|---|---|
| `DATABASE_URL` | `localhost` |
| `DATABASE_PORT` | `5432` |
| `DATABASE_USERNAME` | `postgres` |
| `DATABASE_PASSWORD` | `secret` (local only) |
| `INIT_DB` | `always` (runs `database-init.sql` with sample data) |

### 3. Start the frontend (port 5173)

```bash
cd frontend
npm install
npm run dev
```

The Vite dev server forwards `/api/*` requests to `http://localhost:8080`. To point it somewhere else, set `API_PROXY_TARGET`, or set `VITE_API_BASE_URL` at build time.

### Tests

```bash
cd backend
./gradlew test
```

## Deployment

In production, the frontend is built with `npm run build` and the output is copied into `backend/src/main/resources/public/`, so one Spring Boot service serves both the app and the API. The backend runs as a container image (pushed to AWS ECR and run on AWS App Runner) and connects to PostgreSQL on AWS RDS. Production database settings are passed in as environment variables, never committed to the repo.
