# Symbols

## Use case

```bash
We work in a small startup and we want to develop a small site quickly.
```

## Prompt 1

- https://pikwy.com/
- https://as.com/noticias/real-madrid/?omnil=mod_esc

```bash
Using the screenshot from the images: snapshot2.png
create the web document index.html and put the html content with a style of HTML5. 
When analyze any snapshot identify the images and replace the images with placeholders from:
https://placehold.co/
put the styles in the folder css. 
Put the development in the folder ./demos/demo_05_symbols/site
```

### Verify

```bash
jwebserver -p 8001 -d "$(pwd)/demos/demo_05_symbols/"
open http://localhost:8001/
```

## Prompt 2

- https://docs.google.com/forms/d/13GTAFsBqqcZF_oTRtYTyLaunjTuawzBmCFciLFbsJpo/viewform?edit_requested=true
- https://pikwy.com/

```bash
Using the screenshot from the images: snapshot.png
create the web document index.html and put the html content with a style of HTML5. 
When analyze any snapshot identify the images and replace the images with placeholders from:
https://placehold.co/
put the styles in the folder css. 
Put the development in the folder ./demos/demo_05_symbols/site2
```

## Verify

```bash
jwebserver -p 8001 -d "$(pwd)/demos/demo_05_symbols/"
open http://localhost:8001/
```