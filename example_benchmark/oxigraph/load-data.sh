#!/bin/sh
curl -X POST -H 'Content-Type: application/n-triples' --data-binary "@$1" "http://localhost:81/store?default"