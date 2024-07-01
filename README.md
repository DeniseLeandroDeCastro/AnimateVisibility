<div align="center">

### Em construção

<img src="https://github.com/DeniseLeandroDeCastro/AnimateVisibility/assets/29150094/ca1e8bfa-a088-42f6-bc03-af1b8e82c97a" width="50" height="50"/>

# Animate visibility

<img src="https://cdn-icons-png.flaticon.com/512/12431/12431059.png" width="100" height="100"/>
  
</div><br>

<div>
  <p>
    Para adicionar efeitos de animação às interfaces do usuário, o Jetpack Compose inclui a API Animation. A API Animation consiste em classes e funções que fornecem uma ampla variedade de opções de animação que podem ser 
    adicionadas aos seus aplicativos. 
  </p>
  <br>

  ## Animating Visibility
  <p>
    Talvez a forma mais simples de animação envolva animar a aparência e o desaparecimento de um elemento <b>composable.</b> Em vez de um componente aparecer e desaparecer instantaneamente, 
    vários efeitos animados podem ser aplicados usando o elemento <b>composable AnimatedVisibility.</b> Por exemplo, os elementos da interface do usuário podem ser feitos 
    para aparecer e desaparecer gradualmente da vista, deslizar para dentro e para fora da posição horizontal ou verticalmente, ou mostrar e ocultar expandindo e encolhendo.
    O requisito mínimo para chamar <b>AnimatedVisibility</b> é um parâmetro de variável do tipo <i>Boolean</i> para controlar se seus elementos filhos <i>Composable</i> são visíveis ou não. 
  </p>
  <p>
    Neste projeto, o elemento <i>composable</i> recebe um texto a ser exibido no botão e um manipulador <i>onClick</i> e o novo valor de estado a ser passado ao manipulador quando o botão é clicado. 
    O botão também aceita uma cor de fundo opcional cujo padrão é azul.
  </p>
</div>

```kotlin
@Composable
fun CustomButton (
  text: String,
  targetState: Boolean,
  onClick: (Boolean) -> unit,
  bgColor: Color = Color.Blue) {

    Button (
      onClick = { onClick(targetState) },
      colors = ButtonDefaults.buttonColors(
        containerColor = bgColor,
        contentColor = Color.White
      )
  ) {
      Text(text)
    }
}
```

<div>
  <p>
    O objetivo do manipulador é alterar o estado visível da caixa com base na seleção do botão.
    Os elementos <i>Composables</i> de <b>Column</b> e <b>Row</b> são usados ​​para exibir dois elementos <i>Composables</i> <b>customButton</b> e <b>box</b>. Os botões recebem o texto a ser exibido, 
    a nova configuração para o estado <b>boxVisible</b> e uma referência ao manipulador <b>onClick.</b> Quando um botão é clicado, ele chama o manipulador e passa o novo valor do estado.
  </p>
  <p>
    Clicar nos botões <b>Show</b> e <b>Hide</b> fará com que a caixa apareça e desapareça instantaneamente sem quaisquer efeitos de animação.
    Os efeitos de animação de visibilidade padrão podem ser adicionados simplesmente substituindo a instrução <i>if</i> por uma chamada para <i>AnimatedVisibility</i> como segue:
  </p>
</div>

```kotlin
AnimatedVisibility(visible = boxVisible) {
  Box(
    modifier = Modifier
      .size(height = 200.dp, widht = 200.dp)
      .background(Color.Blue)
  )
}
```

<div align="center">

### O Preview:<br>
 
<img src="https://github.com/DeniseLeandroDeCastro/AnimateVisibility/assets/29150094/6108558e-1476-4653-b88a-ab45a46e2799"/>

</div>

<p>
Quando o aplicativo for testado, a ocultação e a exibição da caixa serão sutilmente animadas. O comportamento padrão do <b>AnimatedVisibility</b> é tão sutil que pode ser difícil notar qualquer diferença. Felizmente, a API Compose Animation oferece uma variedade de opções de personalização. A primeira opção permite que diferentes efeitos de animação sejam definidos quando os elementos secundários composables aparecem e desaparecem (chamados de animações de entrada e saída).
</p>


