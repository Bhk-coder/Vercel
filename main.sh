#!/bin/bash
set -e

echo "Starting build process..."

# 1. Clone the repository FIRST
git clone "$GIT_REPOSITORY_URL" /home/app/output

# 2. Navigate into the cloned code
cd /home/app/output

# 3. Install dependencies and build
npm install
npm run build

# 4. Detect output directory
if [ -d "dist" ]; then
  OUTPUT_DIR="dist"
elif [ -d "build" ]; then
  OUTPUT_DIR="build"
else
  echo "Error: Neither dist/ nor build/ directory was generated."
  exit 1
fi

# 5. Sync to S3
echo "Deploying ${OUTPUT_DIR}/ to S3 bucket ${AWS_S3_BUCKET_NAME}..."
aws s3 sync "$OUTPUT_DIR" "s3://${AWS_S3_BUCKET_NAME}/builds/${PROJECT_ID}"

echo "Deployment finished successfully."