( 
{ 
var trig, note, sig, son, sweep, bassenv, bd, sd, swr, adjust; 

trig = Impulse.kr(0.5); 

note = Demand.kr(trig, 0, Dxrand([102,95,83,79,75,70,28,28,28,28,28,27,27,27,25,25,25,23,23,23,20].midicps, inf)).poll; 

swr = Demand.kr(trig, 0, Drand([0.5, 1, 2, 3, 4, 6], inf)); 
sweep = LFTri.ar(swr).exprange(40, 3000); 

sig = SoundIn.ar(); 
son = Normalizer.ar(sig, 1, 0.1);

son = LPF.ar(son, sweep);	
son = Normalizer.ar(son,0.8); 
son = son + BPF.ar(son, 1500, 2); 

//////// special flavours: 
// hi manster 
son = Select.ar(TRand.kr(trig: trig) < 0.4, [son, HPF.ar(son, 2000) * 4]); 
// sweep manster 
son = Select.ar(TRand.kr(trig: trig) < 0.5, [son, HPF.ar(son, sweep*0.5) * 4]); 
// decimate 
son = Select.ar(TRand.kr(trig: trig) < 0.2, [son, son.round(0.2)]); 

   son = son + GVerb.ar(son, 9, 0.1, 0.7, mul: 0.1); 
son = (son * 2).clip2; 
son = RLPF.ar(son + SinOsc.ar(note,0,LFTri.ar(swr,mul:2,add:1)).tanh, note*4, 0.4); 

bassenv = Decay.ar(T2A.ar(Demand.kr(Impulse.kr(4),0,Dseq([1,0,0,1,0,0,0,0],inf))),0.7); 
bd = FreeVerb.ar(SinOsc.ar(40+(bassenv**3*200),0,7*bassenv).clip2); 
sd = LPF.ar(4*PinkNoise.ar*Decay.ar(Impulse.ar(0.5,0.5),0.4), 3000); 
sd = (sd + BPF.ar(4*sd,1200)).clip2; 

FreeVerb.ar(((son*0.5)+bd+sd+sig).tanh, 0.3, 0.65); 
}.play 
)
