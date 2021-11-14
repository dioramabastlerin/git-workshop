FROM gitpod/workspace-full

USER gitpod

RUN brew install kotlin 

RUN echo test

# Temporarily reset ~/.rvmrc
RUN echo "rvm_gems_path=/home/gitpod/.rvm" > ~/.rvmrc

# Do somer ruby voodoo
COPY --chown=gitpod:gitpod Gemfile /tmp/
COPY --chown=gitpod:gitpod Gemfile.lock /tmp/
WORKDIR /tmp
RUN bash -lc "bundle install"

# Set the ~/.rvmrc back
RUN echo "rvm_gems_path=/workspace/.rvm" > ~/.rvmrc

RUN echo "PS1='\[\e[0;34m\]\]\W\[\e[0m\] \$ '" >> /home/gitpod/.bashrc

RUN git config --global pull.rebase false

WORKDIR /home/gitpod



