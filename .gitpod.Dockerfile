FROM gitpod/workspace-full

USER gitpod


RUN brew install kotlin 

RUN echo test

# Temporarily reset ~/.rvmrc
#RUN echo "rvm_gems_path=/home/gitpod/.rvm" > ~/.rvmrc

# Do some ruby voodoo
# COPY --chown=gitpod:gitpod Gemfile /tmp/
# COPY --chown=gitpod:gitpod Gemfile.lock /tmp/
# WORKDIR /tmp
# RUN bash -lc "bundle install"

# Set the ~/.rvmrc back
# RUN echo "rvm_gems_path=/workspace/.rvm" > ~/.rvmrc


RUN echo "PS1='\[\e[0;34m\]\]\W\[\e[0m\] \$ '" >> /home/gitpod/.bashrc

RUN git config --global pull.rebase false \
    && git config --global merge.conflictStyle diff3

WORKDIR /home/gitpod

RUN bash -lc gem install --user-install jekyll bundler minima jekyll-feed jekyll-remote-theme just-the-docs jekyll-optional-front-matter jekyll-relative-links jekyll-default-layout ffi


