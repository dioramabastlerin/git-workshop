FROM gitpod/workspace-full


USER gitpod
RUN brew install ruby kotlin
COPY --chown=gitpod:gitpod Gemfile /home/gitpod/
COPY --chown=gitpod:gitpod Gemfile.lock /home/gitpod/
RUN gem install bundler
RUN bundle install
