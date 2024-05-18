FROM ruby:3.0.2

ENV RAILS_ENV=production
RUN apt-get update -qq && apt-get install -y build-essential

RUN mkdir /app
WORKDIR /app

COPY Gemfile ./
RUN bundle install
COPY . .
EXPOSE 3000
CMD ["bundle", "exec", "rails", "server", "-b", "0.0.0.0"]