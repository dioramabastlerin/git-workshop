FROM gitpod/workspace-full

USER gitpod

RUN bash -lc gem install jekyll bundler minima jekyll-feed jekyll-remote-theme just-the-docs jekyll-optional-front-matter jekyll-relative-links jekyll-default-layout ffi

RUN brew install kotlin 

RUN echo "PS1='\[\e[0;34m\]\]\W\[\e[0m\] \$ '" >> /home/gitpod/.bashrc \
    && git config --global pull.rebase false \
    && git config --global merge.conflictStyle diff3

WORKDIR /home/gitpod



