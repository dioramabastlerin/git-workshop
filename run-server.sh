#!/bin/bash
bundle exec jekyll serve -H localhost &
sleep 5

echo -e "\n\nRun the GitPod local companion (https://www.gitpod.io/blog/local-app)\nthen open http://localhost:4000"
