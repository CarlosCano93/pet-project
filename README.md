# What is this project about?
**This is for fun purposes only.**

I test new ideas or approaches that came to my mind in order to see if they have sense.
Also, to test new things like records, GitHub Actions, or Heroku on-demand environments.

Right now there is one project, simple integration with PokeApi.

## PokeApi
There is a simple integration with [pokeapi.co](https://pokeapi.co/) but with some ideas about the architecture.

### Exploring ideas
Some colleagues (JS or python devs) always told me that java is too verbose.
So, I try to **do things in the simplest way without removing features** or coverage.
Also, I usually work with hexagonal architecture, and sometimes it over-increases complexity in simple projects.
My idea was to do it in some way that can be **divided into layers but without modularization**.

### One file per context with more than one class
We have just four files with less than 50 lines per file.
That's why **I put several classes in the same file**.
Because they are too small, and we are speaking about **the same context**.

- Controller: The entry point (API) and the Response record. The record is private, only used here.
- Service: The interface, the implementation, and the domain record. The implementation is private and the interface and record are public.
- Client: The interface, the implementation, the feign, and the pokeapi DTO record. Everything is private but the interface.
- ErrorHandler: controller advice, the exceptions, and the record error to inform.

### Isolated?
The original idea was to isolate by layers as it happens in onion architecture with a simpler structure.

- The implementation classes are private, so we have to use the interfaces. (is this necessary?)
- The models are isolated, dto from the client is only accessible from the client and the response from the controller.

Probably we can say that this objective is achieved.

### Conclusions
Maybe this has some sense here because is a very small and specific idea. But in a real project, I don't think so.

But, there are a couple of ideas that I liked. 

#### Inner Classes
Now, I'm less refused to use inner classes.
For example, putting the Feign configuration inside feign client.
Usually, we just configure the object mapper or decoder, and the feign clients aren't too big either.

Another example is to create records inside other records.
Sometimes we need a lot of dto's to deserialize some responses. The idea to have only one file with all data is amazing.
Look at this:

    private record PokemonDto(String name, List<Type> types, Species specie, List<Stat> stats) {
        private record Type(int slot, TypeName type) {}
        private record TypeName(String name) {}
        private record Species(String name, String url) {}
        private record Stat(int baseState, int effort)
    }

We have five POJOS in six lines. We can also add the builder or mapper and still be readable! 

#### Exceptions scope
The ErrorHandler is in the domain package. I don't like this.
I was able to do it because I'm only checking domain exception. 
But if I want to check an adapter custom exception I would have to move it.
Then change the scope of the exception to public. 

#### Interface
Why am I using interfaces?

I guess I'm used to doing it, but I don't find it very useful here. 
The contract is the same with the implementation if we use the public and private methods.

## URL Shortener
Two endpoints. One to save the url and get the identifier hash. Other to redirect with the hash.

Working on it...

TODO: 

- Improve the hash generator
- Error catching 
- Persistence in DB
- Cache
