#!/bin/sh

# URL of the external database
URL="https://brow.com:3600"
MAX_RETRIES=10
RETRY_INTERVAL=5  # Time between retries (in seconds)
retry_count=0

# Credentials from environment variables
DB_USER="${DB_USER:-default-user}"  # Use a default if not provided
DB_PASSWORD="${DB_PASSWORD:-default-password}"

# Function to check if the database is healthy
check_db_health() {
  curl -s --head --request GET $URL --user $DB_USER:$DB_PASSWORD | grep "200 OK" > /dev/null
}

# Loop until the database is healthy or retries are exhausted
until check_db_health; do
  retry_count=$((retry_count+1))

  if [ $retry_count -ge $MAX_RETRIES ]; then
    echo "Database did not become healthy after $MAX_RETRIES retries. Exiting..."
    exit 1
  fi

  echo "Waiting for database to be healthy at $URL (Attempt: $retry_count/$MAX_RETRIES)..."
  sleep $RETRY_INTERVAL
done

echo "Database is healthy. Starting the service."
exec "$@"
