FROM gitpod/workspace-full

USER gitpod

RUN bash -lc "gem install jekyll bundler minima jekyll-feed jekyll-remote-theme just-the-docs jekyll-optional-front-matter jekyll-relative-links jekyll-default-layout ffi"

RUN brew install kotlin 

RUN echo "PS1='\W \$ '" >> /home/gitpod/.bashrc \
    && git config --global pull.rebase false \
    && git config --global merge.conflictStyle diff3 \
    && git config --global init.defaultBranch main

WORKDIR /home/gitpod



