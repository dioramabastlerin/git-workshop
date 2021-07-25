FROM gitpod/workspace-full

USER gitpod

RUN brew install kotlin 

# Temporarily reset ~/.rvmrc
RUN echo "rvm_gems_path=/home/gitpod/.rvm" > ~/.rvmrc

# Do somer ruby voodoo
COPY --chown=gitpod:gitpod Gemfile /tmp/
COPY --chown=gitpod:gitpod Gemfile.lock /tmp/
WORKDIR /tmp
RUN bash -lc "bundle install"

# Set the ~/.rvmrc back
RUN echo "rvm_gems_path=/workspace/.rvm" > ~/.rvmrc

WORKDIR /home/gitpod

