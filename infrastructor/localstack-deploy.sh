#!/bin/bash

set -e

echo "Deploying stack..."

if aws --endpoint-url=http://localhost:4566 cloudformation deploy \
    --stack-name patient-management \
    --template-file "../cdk.out/localstack.template.json"
then
    echo "Deployment successful"

    echo "Fetching Load Balancer DNS..."
    aws --endpoint-url=http://localhost:4566 elbv2 describe-load-balancers \
        --query "LoadBalancers[0].DNSName" --output text || echo "No LB found"
else
    echo "Deployment failed. Fetching stack events..."

    aws --endpoint-url=http://localhost:4566 cloudformation describe-stack-events \
        --stack-name patient-management

    exit 1
fi