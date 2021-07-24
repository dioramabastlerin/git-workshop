FROM gitpod/workspace-full

USER gitpod
RUN brew install kotlin
RUN echo jekyll
RUN bundle install
