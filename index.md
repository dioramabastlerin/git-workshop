---
layout: home
title: Kapitel26 Git-Workshop
description: "Präsentationen und Übungen zu dem Kapitel26 Git-Workshops"
nav_exclude: true
---

# Git Workshops

# Kapitel 26 - Git Lernen

## Workshops

{% for p in site.pages %}
    {% if p.layout == "workshop" %}
* [{{ p.title }}](/git-workshop{{ p.url }})
  {% endif %} 
{% endfor %}
  
## Blog

* [Kapitel 26 Blog](https://kapitel26.github.io){:target="_blank"}

## Repositorys

* [GitHub](https://github.com/bstachmann/git-workshop){:target="_blank"}
* [GitLab](https://gitlab.com/bjoern.stachmann/git-workshop){:target="_blank"}


