(
x = { | gain=1, amp=1, rq=0.9, band1=12, band2=12, band3=12, band4=6, band5=0, band6=6, band7=12, band8=7, band9=0, band10=0 |
    var in, amount, amCoef, dist, eq1, eq2, eq3, eq4, eq5, eq6, eq7, eq8, eq9, eq10;
    
    //in = SoundIn.ar(0) * gain;
    in = HPF.ar(SoundIn.ar(0), 400)*5;
    amount = 0.8;
    amCoef= 2*amount/(1-amount);
    dist = MidEQ.ar(LPF.ar((1+amCoef)*in/(1+(amCoef*in.abs)), [3800, 3900])*0.5, 120, 0.7, 8);
    eq1 = MidEQ.ar(dist,31.25,Lag.kr(rq,0.3),band1);
    eq2 = MidEQ.ar(eq1,62.5,Lag.kr(rq,0.3),band2);
    eq3 = MidEQ.ar(eq2,125,Lag.kr(rq,0.3),band3);
    eq4 = MidEQ.ar(eq3,250,Lag.kr(rq,0.3),band4);
    eq5 = MidEQ.ar(eq4,500,Lag.kr(rq,0.3),band5);
    eq6 = MidEQ.ar(eq5,1000,Lag.kr(rq,0.3),band6);
    eq7 = MidEQ.ar(eq6,2000,Lag.kr(rq,0.3),band7);
    eq8 = MidEQ.ar(eq7,4000,Lag.kr(rq,0.3),band8);
    eq9 = MidEQ.ar(eq8,8000,Lag.kr(rq,0.3),band9);
    eq10 = MidEQ.ar(eq9,16000,Lag.kr(rq,0.3),band10);
    Pan2.ar(eq10,0,0.9);

}.play
)

x.set(\band1, 12);
x.set(\band1, MouseY.kr(0,1,0.01));
x.set(\band2, 12);
x.set(\band3, 12);
x.set(\band4, 6);
x.set(\band5, 0);
x.set(\band6, 0);
x.set(\band7, 0);
x.set(\band8, 0);
x.set(\band9, 0);
x.set(\band10, 0);
x.set(\rq, 0.9)
x.set(\gain, 100);
x.set(\amp, 0.2);

(
{
    var in, amount, amCoef;
    in = HPF.ar(SoundIn.ar(0), 400)*5;
    amount = 0.8;
    amCoef= 2*amount/(1-amount);
    MidEQ.ar(LPF.ar((1+amCoef)*in/(1+(amCoef*in.abs)), [3800, 3900])*0.5, 120, 0.7, 8);
}.play;
)

play{LADSPA.ar(2, 1779,PinkNoise.ar(LFNoise1.ar(10)),0.5,0.7,0.5,0.7)}

(
{
    var in, dist, pan;
    in = SoundIn.ar(0);
    // ampV: in, gain 0:3, bass -9:9, tone 0:1, drive 0.0001:1, watts 5:150, out, latency
    dist = LADSPA.ar(1, 2587, in, 2.0, 5, 0.5, 0.75, 60);
    pan = Pan2.ar(dist, 0, 0.5);
}.play;
)

// distorted sound
(
x =
{   | dist=1 |
    var in, gxdist, gate, eq, amp, compress, cabinet, pan;
    in = SoundIn.ar(0);

    //  guitarix_distortion
    // in
    // (out)
    // overdrive 1:20, 10.5
    // overdrive 0/1, 0
    // drive 0:1, 0.5
    // drivelevel 0:1, 0
    // drivegain -20:20, 0
    // highpass 8:1000, 256
    // lowpass 1000:10000, 5500
    // lowhighpass 0/1, 1
    // highcut 1000:10000, 5500
    // lowcut 8:1000, 256
    // lowhighcut 0/1, 1
    // trigger 0:1, 1
    // vibrato 0.01:1, 1
   
    if(dist == 1,{
        gxdist = LADSPA.ar(1, 4061, in, 11.3, 0, 0.5, 0, 20, 256, 5500, 0, 5500, 256, 0, 1, 1);

        // hard_gate
        // threshold 0:1
        gate = LADSPA.ar(1, 1845, 0.0139, gxdist);
    },{
        gate = in;
    });

    // C* eq (all -48:30)
    // 31Hz
    // 63Hz
    // 125Hz
    // 250Hz
    // 500Hz
    // 1kHz
    // 2kHz
    // 4kHz
    // 8kHz
    // 16kHz

    eq = LADSPA.ar(1, 1773, gate, -19.6, -7.82, -1.12, -14.1, -3.48, 9.52, 9.17, -2.56, 2.36, 0);

    // ampV
    // in
    // gain 0:3
    // bass -9:9
    // tone 0:1
    // drive 0.0001:1
    // watts 5:150

    amp = LADSPA.ar(1, 2587, eq, 2.5, -0.519, 0.461, 0.801, 60.4);

    // c* compress
    // gain 0:24
    // ratio 1:10
    // attack 0.000977:1
    // release 0.000977:1
    // threshold -30:400
    // knee radius 1:10

    compress = LADSPA.ar(1, 1772, amp, 5.33, 1.04, 0.000977, 0.105, -1.5, 3.49);

    // c* cabinetII
    // model 0/7
    // gain -24:24

    cabinet = LADSPA.ar(1, 2581, compress, 5, 6.99);

    pan = Pan2.ar(cabinet, 0, 0.3);

}.play;
)

x.set(\dist, 0);
